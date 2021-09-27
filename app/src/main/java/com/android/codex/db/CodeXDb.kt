package com.android.codex.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.codex.data.Category

@Database(
    entities = [Category::class],
    version = 1,
    exportSchema = false
)

abstract class CodeXDb : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao
}