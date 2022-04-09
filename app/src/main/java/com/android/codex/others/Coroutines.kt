package com.android.codex.others

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

fun getCoroutineContext(): CoroutineContext {
    return Dispatchers.Main + Job()
}