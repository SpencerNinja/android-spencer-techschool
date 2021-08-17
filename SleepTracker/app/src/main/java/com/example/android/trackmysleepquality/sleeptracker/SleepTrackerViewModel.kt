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
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import android.provider.SyncStateContract.Helpers.update
import androidx.lifecycle.*
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.formatNights
import kotlinx.coroutines.*

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
        // pass in instance of SleepDatabaseDao
        // pass in application to access strings and styles
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {

                // Create a viewModelJob and override onCleared() for canceling coroutines
                private var viewModelJob = Job()

                override fun onCleared() {
                        super.onCleared()
                        viewModelJob.cancel()
                }

                // Define a scope for the coroutines to run in
                private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

                // Create tonight live data var and use a coroutine to initialize it from the database
                private var tonight = MutableLiveData<SleepNight?>()

                // Get all nights from the database
                private val nights = database.getAllNights()

                // convert nights to a string for displaying
                val nightsString = Transformations.map(nights) { nights ->
                        formatNights(nights, application.resources)
                }

                val startButtonVisible = Transformations.map(tonight) {
                        null == it
                }

                val stopButtonVisible = Transformations.map(tonight) {
                        null != it
                }

                val clearButtonVisible = Transformations.map(nights) {
                        it?.isNotEmpty()
                }

                private var _showSnackbarEvent = MutableLiveData<Boolean>()

                val showSnackBarEvent: LiveData<Boolean>
                        get() = _showSnackbarEvent

                fun doneShowingSnackbar() {
                        _showSnackbarEvent.value = false
                }

                private val _navigateToSleepQuality = MutableLiveData<SleepNight>()

                val navigateToSleepQuality: LiveData<SleepNight>
                        get() = _navigateToSleepQuality

                fun doneNavigating() {
                        _navigateToSleepQuality.value = null
                }

                init {
                        initializeTonight()
                }
                private fun initializeTonight() {
                        uiScope.launch {
                                tonight.value = getTonightFromDatabase()
                        }
                }

                /*
                * Handling the case of the stopped app or forgotten recording,
                * the start and end times will be the same.
                *
                * If the start time and end time are not the same, then we do not have an unfinished recording.
                */
                private suspend fun getTonightFromDatabase(): SleepNight? {
                        return withContext(Dispatchers.IO) {
                                var night = database.getTonight()
                                if (night?.endTimeMilli != night?.startTimeMilli) {
                                        night = null
                                }
                                night
                        }
//                        var night = database.getTonight()
//                        if (night?.endTimeMilli != night?.startTimeMilli) {
//                                night = null
//                        }
//                        return night
                }

                // Add local functions for insert(), update(), and clear()
                fun onStartTracking() {
                        uiScope.launch {
                                val newNight = SleepNight()
                                // insert into database
                                insert(newNight)
                                tonight.value = getTonightFromDatabase()
                        }
                }

                // insert()
                private suspend fun insert(night: SleepNight) {
                        withContext(Dispatchers.IO) {
                                database.insert(night)
                        }
//                        database.insert(night)
                }

//                fun someWorkNeedsToBeDone() {
//                        uiScope.launch {
//                                suspendFunction()
//                        }
//                }

//                suspend fun suspendFunction() {
//                        withContext(Dispatchers.IO) {
//                                longrunningWork()
//                        }
//                }

                // Implement click handlers for Start, Stop, and Clear buttons using coroutines to do the database work
                fun onStopTracking() {
                        viewModelScope.launch {
                                val oldNight = tonight.value ?: return@launch
                                oldNight.endTimeMilli = System.currentTimeMillis()
                                update(oldNight)
                                _navigateToSleepQuality.value = oldNight
                        }
                }

                private suspend fun update(night: SleepNight) {
                        withContext(Dispatchers.IO) {
                                database.update(night)
                        }
//                        database.update(night)
                }

                // Executes when the CLEAR button is clicked.
                fun onClear() {
                        viewModelScope.launch {
                                clear()
                                tonight.value = null
                                _showSnackbarEvent.value = true
                        }
                }

                private suspend fun clear() {
                        withContext(Dispatchers.IO) {
                                database.clear()
                        }
//                        database.clear()
                }

                // Transform nights into a nightsString using formatNights()

}

// Coroutines
        // make sure long-running database operations don't slow down the app for the user
