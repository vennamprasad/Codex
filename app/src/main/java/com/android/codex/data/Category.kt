package com.android.codex.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Category")
data class Category(
    val title: String? = "",
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)
data class CategoryResponse(
    val data : List<Category>,
    val status : String ?=""
)