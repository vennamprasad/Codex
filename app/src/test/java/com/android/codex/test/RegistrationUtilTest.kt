package com.android.codex.test

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username and password`() {
        val result = RegistrationUtil.validateUserRegistration(
            "", "", ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `username exists`() {
        val result = RegistrationUtil.validateUserRegistration(
            "prasad", "123", "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password matched`() {
        val result = RegistrationUtil.validateUserRegistration(
            "prasad", "123", "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password not matched`() {
        val result = RegistrationUtil.validateUserRegistration(
            "prasad", "123", "1234"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password lenth should be grater than 2`() {
        val result = RegistrationUtil.validateUserRegistration(
            "prasad", "ab", "1"
        )
        assertThat(result).isFalse()
    }

}