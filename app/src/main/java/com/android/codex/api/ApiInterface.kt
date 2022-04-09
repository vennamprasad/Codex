package com.android.codex.api

import com.android.codex.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("")
    suspend fun search(
        @Query("q") search: String,
        @Query("key") key: String = BuildConfig.API_KEY
    ): Response<Any>
}