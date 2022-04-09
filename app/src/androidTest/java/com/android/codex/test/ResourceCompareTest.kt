package com.android.codex.test

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class ResourceCompareTest {

    private lateinit var resourceCompare: ResourceCompare

    @Before
    fun setup() {
        resourceCompare = ResourceCompare()
    }

    /*@Test
    fun checkSameResource() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.isEqual(context, R.string.app_name, "Code-x")
        assertThat(result).isTrue()
    }

    @Test
    fun checkDiffResource() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.isEqual(context, R.string.app_name, "Code")
        assertThat(result).isFalse()
    }*/
}