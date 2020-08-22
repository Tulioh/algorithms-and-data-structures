package com.tuliomagalhaes.reversestring

import java.lang.StringBuilder

/*fun String.reverse(): String {
    return this.reversed()
}

fun String.reverse(): String {
    val newStr = StringBuilder()
    for (i in this.length - 1 downTo 0) {
        newStr.append(this[i])
    }
    return newStr.toString()
}

fun String.reverse(): String {
    var newStr = ""
    this.forEach {
        newStr = it + newStr
    }
    return newStr
}*/

fun String.reverse(): String {
    return this.split("").reduce { reversed, character ->
        character + reversed
    }
}