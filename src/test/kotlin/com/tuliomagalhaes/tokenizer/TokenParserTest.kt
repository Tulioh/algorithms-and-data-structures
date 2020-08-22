package com.tuliomagalhaes.tokenizer

import org.junit.Assert.*
import org.junit.Test

internal class TokenParserTest {

    private val tokenParser = TokenParser()

    @Test
    fun execute_should_evaluate_expression_null_as_null() {
        // Given
        val expression = "null"

        // When
        val result = tokenParser.parse(expression)

        // Then
        assertNull(result)
    }

    @Test
    fun execute_should_evaluate_expression_true_as_true() {
        // Given
        val expression = "true"

        // When
        val result = tokenParser.parse(expression)

        // Then
        assertTrue(result as Boolean)
    }

    @Test
    fun execute_should_evaluate_expression_false_as_false() {
        // Given
        val expression = "false"

        // When
        val result = tokenParser.parse(expression)

        // Then
        assertFalse(result as Boolean)
    }

    @Test
    fun execute_should_evaluate_expression_1_as_1() {
        // Given
        val expression = "1"

        // When
        val result = tokenParser.parse(expression)

        // Then
        assertEquals(1L, result)
    }

    @Test
    fun execute_should_evaluate_expression_binding_as_todo() {
        // Given
        val expression = "binding"

        // When
        val result = tokenParser.parse(expression)

        // Then
        assertEquals("TODO", result)
    }

    @Test
    fun execute_should_evaluate_expression_binding_with_id_as_todo() {
        // Given
        val expression = "bindingId.bindingValue"

        // When
        val result = tokenParser.parse(expression)

        // Then
        assertEquals("TODO", result)
    }

    @Test
    fun execute_should_evaluate_expression_1_0_as_1_0() {
        // Given
        val expression = "1.0"

        // When
        val result = tokenParser.parse(expression)

        // Then
        assertEquals(1.0, result)
    }

    @Test
    fun execute_should_evaluate_gt_1_and_2_as_false() {
        // Given
        val expression = "gt(1, 2)"

        // When
        val result = tokenParser.parse(expression)

        // Then
        assertFalse(result as Boolean)
    }

    @Test
    fun execute_should_evaluate_conditional_first_param() {
        // Given
        val expression = "conditional(true, 'This is true', 'This is not true')"

        // When
        val result = tokenParser.parse(expression)

        // Then
        assertEquals("This is true", result)
    }

    @Test
    fun execute_should_evaluate_conditional_second_param() {
        // Given
        val expression = "conditional(false, 'This is true', 'This is not true')"

        // When
        val result = tokenParser.parse(expression)

        // Then
        assertEquals("This is not true", result)
    }

    @Test
    fun execute_should_evaluate_sum_1_and_2_as_true() {
        // Given
        val expression = "gt(sum(1, 1), 1)"

        // When
        val result = tokenParser.parse(expression)

        // Then
        assertTrue(result as Boolean)
    }

    @Test
    fun execute_should_evaluate_gt_1_and_sum_1_and_2_as_true() {
        // Given
        val expression = "gt(sum(1, 1), 1)"

        // When
        val result = tokenParser.parse(expression)

        // Then
        assertTrue(result as Boolean)
    }

    @Test
    fun execute_should_evaluate_conditional_with_gt_and_sum() {
        // Given
        val expression = "conditional(gt(sum(1, 1), 1), 1, 2)"

        // When
        val result = tokenParser.parse(expression)

        // Then
        assertEquals(1L, result)
    }
}