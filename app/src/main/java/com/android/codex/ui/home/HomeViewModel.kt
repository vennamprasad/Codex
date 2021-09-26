package com.android.codex.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.codex.data.Category

class HomeViewModel : ViewModel() {

    private val data = MutableLiveData<ArrayList<Category>>().apply {

        //generating dummy list
        val items: ArrayList<Category> = ArrayList()
            for (i in 0 until 100) {
                items.add(Category(title = "Item $i"))
            }
        value=items

    }
    val itemsData: LiveData<ArrayList<Category>> = data
}