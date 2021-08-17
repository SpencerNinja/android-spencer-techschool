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

package com.example.android.devbyteviewer.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Stores a collection of Database videos in a room database
 */
@Dao
interface VideoDao {
    // returns a list of videos (loads values)
    @Query("select * from video_table")
    // must be LiveData so we can see when it changes
    fun getVideos(): LiveData<List<DatabaseVideo>>

    // save new or changed videos into the database (stores values)
    // OnConflictStrategy tells the database what to do with the data when it encounters conflicts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // vararg: a variable number of arguments
    fun insertAll(vararg videos: DatabaseVideo)
}

// extends VideoDao
@Database(entities = [DatabaseVideo::class], version = 1)
abstract class VideosDatabase: RoomDatabase() {
    abstract val videoDao: VideoDao
}

// Singleton = an object that can only have one instance
private lateinit var INSTANCE: VideosDatabase

fun getDatabase(context: Context): VideosDatabase {
    // Instantiate INSTANCE
    // wrap in 'synchronized' to make it thread-safe
    synchronized(VideosDatabase::class.java) {
        // if Instance is not initialized
        if (!::INSTANCE.isInitialized) {
            // create a new instance
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                VideosDatabase::class.java,
                "videos").build()
        }
    }
    return INSTANCE
}
