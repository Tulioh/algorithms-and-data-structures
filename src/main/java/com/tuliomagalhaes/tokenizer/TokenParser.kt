package com.tuliomagalhaes.tokenizer

import com.tuliomagalhaes.tokenizer.function.FunctionResolver
import java.lang.IllegalStateException

class TokenParser(
    private val functionResolver: FunctionResolver = FunctionResolver()
) {

    fun parse(expression: String): Any? {
        val interpreter = TokenInterpreter(expression)

        return interpreter.nextToken()?.let {
            return parseToken(interpreter, it)
        } ?: {
            throw IllegalStateException("Invalid expression")
        }
    }

    private fun parseToken(interpreter: TokenInterpreter, token: Token): Any? {
        return when (token.type) {
            TokenType.NUMBER, TokenType.STRING, TokenType.BOOLEAN, TokenType.NULL, TokenType.BINDING -> {
                parseValue(token)
            }
            TokenType.FUNCTION -> parseFunction(token, interpreter)
            else -> throw IllegalStateException("")
        }
    }

    private fun parseFunction(token: Token, interpreter: TokenInterpreter): Any? {
        val params = mutableListOf<Any?>()

        do {
            val nextToken = interpreter.nextToken()
            if (nextToken != null) {
                if (nextToken.type == TokenType.CLOSE_BRACKET) {
                    break
                } else if (nextToken.type == TokenType.COMMA) {
                    continue
                } else {
                    params.add(parseToken(interpreter, nextToken))
                }
            }
        } while (nextToken != null)

        return functionResolver.execute(token.value, params)
    }

    private fun parseValue(token: Token): Any? {
        return when (token.type) {
            TokenType.NUMBER -> {
                (token as TokenNumber).numberValue
            }
            TokenType.BOOLEAN -> {
                (token as TokenBoolean).booleanValue
            }
            TokenType.BINDING -> {
                "TODO"
            }
            TokenType.STRING -> {
                token.value
            }
            else -> null
        }
    }
}