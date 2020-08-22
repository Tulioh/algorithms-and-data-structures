package com.tuliomagalhaes.maxchar

fun String.maxChar(): String {
    val chars = mutableMapOf<Char, Int>()
    var maxChar = ""
    var frequency = 0

    this.forEach {
        val count = (chars[it] ?: -1) + 1
        chars[it] = count

        if (count > frequency || maxChar == "") {
            frequency = count
            maxChar = it.toString()
        }
    }

    return maxChar
}