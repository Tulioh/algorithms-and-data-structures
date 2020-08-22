package com.tuliomagalhaes.tokenizer

enum class TokenType {
    FUNCTION,
    BINDING,
    OPEN_BRACKET,
    CLOSE_BRACKET,
    COMMA,
    NUMBER,
    BOOLEAN,
    STRING,
    NULL
}

open class Token(
    val value: String,
    val type: TokenType
)

class TokenNumber(
    val numberValue: Number,
    value: String
) : Token(value, TokenType.NUMBER)

class TokenBoolean(
    val booleanValue: Boolean,
    value: String
) : Token(value, TokenType.BOOLEAN)

class TokenFunction(
    val params: List<Token?>,
    value: String
) : Token(value, TokenType.FUNCTION)

fun tokenOpenBracket() = Token("(", TokenType.OPEN_BRACKET)
fun tokenOfCloseBracket() = Token(")", TokenType.CLOSE_BRACKET)
fun tokenOfComma() = Token(",", TokenType.COMMA)
fun tokenOfNumber(value: Number, tokenString: String) = TokenNumber(value, tokenString)
fun tokenOfBoolean(value: Boolean, tokenString: String) = TokenBoolean(value, tokenString)
fun tokenOfString(value: String) = Token(value, TokenType.STRING)
fun tokenOfFunction(value: String) = Token(value, TokenType.FUNCTION)
fun tokenOfBinding(value: String) = Token(value, TokenType.BINDING)
fun tokenOfNull() = Token("", TokenType.NULL)