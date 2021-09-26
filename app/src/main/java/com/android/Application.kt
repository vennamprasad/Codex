package com.android

import android.app.Application
import com.android.codex.BuildConfig
import timber.log.Timber

open class CodeXApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}