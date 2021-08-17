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

package com.example.android.trackmysleepquality.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

// DAO = Database Access Object
    // map Kotlin functions to SQL queries

// The queries @Insert and @Update run built-in SQL queries
    // @Query lets you input and run any SQL query

@Dao
interface SleepDatabaseDao {

    // TODO Why do we suspend these methods?

    // Add Annotated insert() method for inserting a single SleepNight
    @Insert
    suspend fun insert(night: SleepNight)

    // Add annotated update() method for updating a SleepNight.
    // When updating a row with a value already set in a column,
    // replaces the old value with the new one.
    @Update
    suspend fun update(night: SleepNight)

    // Add annotated get() method that gets the SleepNight by key.
    // Selects and returns the row that matches the supplied start time, which is our key.
    @Query("SELECT * from daily_sleep_quality_table WHERE nightId = :key")
    suspend fun get(key: Long): SleepNight?

    // Add annotated clear() method and query, delete all rows.
    // This does not delete the table, only its contents.
    @Query("DELETE FROM daily_sleep_quality_table")
    suspend fun clear()

    // Add annotated getAllNights() method and query, get all rows.
    // Selects and returns all rows in the table.
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC")
    // Room makes sure live data is updated whenever the database is updated
    // This means we only have to get this list once and attach an observer to it
    fun getAllNights(): LiveData<List<SleepNight>>

    // Add annotated getTonight() method and query.
    // Selects and returns the latest night.
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
    suspend fun getTonight(): SleepNight?

}
