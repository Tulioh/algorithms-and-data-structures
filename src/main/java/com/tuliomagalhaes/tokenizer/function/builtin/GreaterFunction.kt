package com.tuliomagalhaes.tokenizer.function.builtin

import com.tuliomagalhaes.tokenizer.function.Function

class GreaterFunction : Function<Boolean> {

    override fun execute(params: List<Any?>): Boolean {
        val value1 = params[0]
        val value2 = params[1]

        return if (value1 is Long && value2 is Long) {
            value1 > value2
        } else {
            (value1 as Double) > (value2 as Double)
        }
    }

    override fun isParametersValid(params: List<Any?>): Boolean {
        return params.size == 2 &&
                (params[0] is Double && params[1] is Double) ||
                (params[0] is Long && params[1] is Long)
    }

    override fun functionName(): String = "gt"
}