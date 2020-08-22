package com.tuliomagalhaes.tokenizer

import java.io.StringReader

class TokenReader(
    value: String
) : StringReader(value) {

    private var peek = -1

    fun ignoreWhitespace(): Char {
        if (peek != -1 && !peek.toChar().isWhitespace()) {
            val ret = peek.toChar()
            peek = -1
            return ret
        }
        while (true) {
            val char = read().toChar()
            if (!char.isWhitespace()) {
                return char
            }
        }
    }

    fun peek(): Int {
        return if (peek != -1) {
            peek
        } else {
            read().also {
                peek = it
            }
        }
    }
}