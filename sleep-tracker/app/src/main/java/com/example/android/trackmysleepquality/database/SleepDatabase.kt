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

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/* A database that stores SleepNight info
* and a global method to get access to the database
*
* Same pattern used for any database
*/

// version: up the version number every time you update the schema
// exportSchema: saves schema to a folder, provides version history of the database
@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
abstract class SleepDatabase : RoomDatabase() {

    // Connects the database to the DAO
    abstract val sleepDatabaseDao: SleepDatabaseDao

    /* Define a companion object.
    * Allows us to add functions on the SleepDatabase class.
    * Clients can call 'SleepDatabase.getInstance(context)' to instantiate a new SleepDatabase.
    */
    // Allows clients to access the methods for creating or getting the database without instantiating the class
    companion object{
        /* Declare a @Volatile INSTANCE variable.
        * INSTANCE will keep a reference to any database returned via getInstance.
        *
        * This will help us avoid repeatedly initializing the database, which is expensive.
        *
        * The value of a volatile variable will never be cached, and all writes and reads will be
        * done to and from the main memory. It means that changes made by one thread to shared data
        * are visible to other threads.
        *
        * Writes to this field are immediately made visible to other threads.
        * */
        // Changes to one thread are immediately visible to other threads
        @Volatile
        private var INSTANCE: SleepDatabase? = null

        // Define a getInstance() method with a synchronized block
        // Returns a reference to the SleepDatabase
        fun getInstance(context: Context): SleepDatabase {
            // Synchronized means that only one thread of execution at a time can enter this block of code
            // which makes sure the database only get initialized once
            synchronized(this){
                // Inside the synchronized block
                    // Check whether the database already exists
                    // and if it does not, use Room.databaseBuilder to create it
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SleepDatabase::class.java,
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}


