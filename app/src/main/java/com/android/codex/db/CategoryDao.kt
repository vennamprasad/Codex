package com.android.codex.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT count(id) FROM Category")
    fun numberOfCategories(): Int // suspend keyword to run in coroutine
}