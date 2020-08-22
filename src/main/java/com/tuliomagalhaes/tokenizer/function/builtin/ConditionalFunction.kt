package com.tuliomagalhaes.tokenizer.function.builtin

import com.tuliomagalhaes.tokenizer.function.Function
import java.lang.IllegalStateException

class ConditionalFunction : Function<Any?> {

    override fun execute(params: List<Any?>): Any? {
        return if (params[0] as Boolean) {
            params[1]
        } else {
            params[2]
        }
    }

    override fun isParametersValid(params: List<Any?>): Boolean {
        return params.size == 3 && params[0] is Boolean
    }

    override fun functionName(): String = "conditional"
}