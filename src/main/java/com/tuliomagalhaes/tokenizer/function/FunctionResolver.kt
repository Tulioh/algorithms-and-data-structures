package com.tuliomagalhaes.tokenizer.function

import com.tuliomagalhaes.tokenizer.function.builtin.ConditionalFunction
import com.tuliomagalhaes.tokenizer.function.builtin.GreaterFunction
import com.tuliomagalhaes.tokenizer.function.builtin.SumFunction

class FunctionResolver {

    private val functions = createFunctions()

    fun execute(functionName: String, params: List<Any?>): Any? {
        val func = functions[functionName]
        require(func?.isParametersValid(params) == true) {
            "Invalid parameters at function \"$functionName\""
        }
        return func?.execute(params)
    }

    private fun createFunctions(): Map<String, Function<*>> {
        val gt = GreaterFunction()
        val sum = SumFunction()
        val conditional = ConditionalFunction()

        return mapOf(
            gt.functionName() to gt,
            sum.functionName() to sum,
            conditional.functionName() to conditional
        )
    }
}