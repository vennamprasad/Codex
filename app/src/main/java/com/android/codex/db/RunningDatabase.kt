package com.android.codex.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.codex.data.Converters
import com.android.codex.data.Run

@Database(
    entities = [Run::class],
    version = 1,exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RunningDatabase : RoomDatabase() {

    abstract fun getRunDao(): RunDAO
}