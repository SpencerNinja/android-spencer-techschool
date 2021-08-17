/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.devbyteviewer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.devbyteviewer.database.VideosDatabase
import com.example.android.devbyteviewer.database.asDomainModel
import com.example.android.devbyteviewer.domain.Video
import com.example.android.devbyteviewer.network.Network
import com.example.android.devbyteviewer.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Dependency Injection:
 * The repository class is responsible for providing a simple API to our data sources.
 * Ask users to pass in the video database as a constructor parameter, this is called dependency injection.
 * By taking a database object as a constructor parameter, we don't need to keep a reference to Android context
 * to our repository, potentially causing leaks.
 */

/**
 * Repository for fetching videos from the network and storing them on disk
 */
class VideosRepository(private val database: VideosDatabase) {

    /**
     * A playlist of videos that can be shown on the screen.
     */
    // from the database
    // Transformations.map lets us convert from one type of LiveData to another
    // asDomainModel helps us convert from a VideoDatabase to a Video
    val videos: LiveData<List<Video>> = Transformations.map(database.videoDao.getVideos()) {
        it.asDomainModel()
    }

    /**
     * Refresh the videos stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To 
     */
    // suspend so it is coroutine friendly
    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            // Ask the Network to get the playlist
            // use await() to tell the coroutine to suspend until it is available
            val playlist = Network.devbytes.getPlaylist().await()
            // make a call on the database to save the videos
            database.videoDao.insertAll(*playlist.asDatabaseModel())
        }
    }
}


