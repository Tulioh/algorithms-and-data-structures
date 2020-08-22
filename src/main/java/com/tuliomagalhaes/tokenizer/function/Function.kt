package com.tuliomagalhaes.tokenizer.function

interface Function<T> {
    fun functionName(): String
    fun isParametersValid(params: List<Any?>): Boolean
    fun execute(params: List<Any?>): T
}