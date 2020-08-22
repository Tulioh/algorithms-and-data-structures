package com.tuliomagalhaes.maxchar

import org.junit.Assert.assertEquals
import org.junit.Test

internal class MaxCharFinderKtTest {

    @Test
    fun maxChar_should_return_max_used_character_when_single_character() {
        // Given
        val str = "a"

        // When
        val result = str.maxChar()

        // Then
        assertEquals("a", result)
    }

    @Test
    fun maxChar_should_return_max_used_character() {
        // Given
        val str = "abcdefghijklmnaaaaa"

        // When
        val result = str.maxChar()

        // Then
        assertEquals("a", result)
    }

    @Test
    fun maxChar_should_return_max_used_character_with_number() {
        // Given
        val str = "ab1c1d1e1f1g1"

        // When
        val result = str.maxChar()

        // Then
        assertEquals("1", result)
    }

    @Test
    fun maxChar_should_return_max_used_character_when_max_is_last() {
        // Given
        val str = "abcda"

        // When
        val result = str.maxChar()

        // Then
        assertEquals("a", result)
    }
}