package com.android.codex.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.android.codex.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TipsViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
}