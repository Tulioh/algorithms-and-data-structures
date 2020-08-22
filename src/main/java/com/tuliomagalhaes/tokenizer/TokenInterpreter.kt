package com.tuliomagalhaes.tokenizer

class TokenInterpreter(value: String) {

    private val reader: TokenReader = TokenReader(value)
    private var lastChar = Char.MIN_VALUE

    fun nextToken(): Token? {
        if (lastChar == Char.MIN_VALUE || lastChar.isWhitespace()) {
            lastChar = reader.ignoreWhitespace()
        }

        return readToken()
    }

    private fun readToken(): Token? {
        if (lastChar.isDigit()) {
            return readNumber()
        }

        when (lastChar) {
            '\'' -> return readString()
            '(' -> {
                resetLastChar()
                return tokenOpenBracket()
            }
            ')' -> {
                resetLastChar()
                return tokenOfCloseBracket()
            }
            ',' -> {
                resetLastChar()
                return tokenOfComma()
            }
        }

        if (!lastChar.isJavaIdentifierStart()) {
            return null
        }

        return readVariableOrFunction()
    }

    private fun readString(): Token? {
        val str = readUnescapedString(reader)
        lastChar = Char.MIN_VALUE
        return tokenOfString(str)
    }

    private fun readUnescapedString(r: TokenReader): String {
        val sb = StringBuilder()
        var c = 0.toChar()
        while (c != '\'') {
            c = r.read().toChar()
            when (c) {
                '\'' -> {
                    val v = r.peek()
                    if (v == '\''.toInt()) {
                        r.read()
                        sb.append('\'')
                        c = 0.toChar()
                    }
                }
                else -> sb.append(c)
            }
        }
        return sb.toString()
    }

    private fun readNumber(): Token? {
        val text = StringBuilder()

        text.append(lastChar)
        lastChar = reader.read().toChar()

        var decimal = false
        while (lastChar.isDigit() || '.' == lastChar) {
            text.append(lastChar)
            if (lastChar == '.') decimal = true
            lastChar = reader.read().toChar()
        }

        val value = text.toString()
        return if (decimal) {
            tokenOfNumber(value.toDouble(), value)
        } else {
            try {
                tokenOfNumber(value.toLong(), value)
            } catch (e: NumberFormatException) {
                null
            }
        }
    }

    private fun readVariableOrFunction(): Token? {
        val sb = StringBuilder()
        while (lastChar.isJavaIdentifierPart()) {
            sb.append(lastChar)
            lastChar = reader.read().toChar()
        }
        if (Character.isWhitespace(lastChar)) {
            lastChar = reader.ignoreWhitespace()
        }

        val value = sb.toString()

        return if (lastChar == '(') {
            lastChar = Char.MIN_VALUE
            tokenOfFunction(sb.toString())
        } else if (value == "true" || value == "false") {
            tokenOfBoolean(value.toBoolean(), value)
        } else if (value == "null") {
            tokenOfNull()
        } else {
            tokenOfBinding(value)
        }
    }

    private fun resetLastChar() {
        lastChar = Char.MIN_VALUE
    }
}