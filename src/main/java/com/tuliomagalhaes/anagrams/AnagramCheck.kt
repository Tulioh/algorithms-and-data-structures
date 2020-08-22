package com.tuliomagalhaes.anagrams

import java.util.*

fun String.isAnagramOf(str: String): Boolean {
    val str1 = this.normalize()
    val str2 = str.normalize()

    return str1 == str2
}

private fun String.normalize(): SortedSet<Char> {
    return this.toLowerCase().filter { it.isLetterOrDigit() }.toSortedSet()
}


/*
fun String.isAnagramOf(str: String): Boolean {
    val str1 = this.toCharCount()
    val str2 = str.toCharCount()

    if (str1.size != str2.size) {
        return false
    }

    for (entry in str1.entries) {
        if (str2[entry.key] != entry.value) {
            return false
        }
    }

    return true
}

private fun String.toCharCount(): SortedMap<Char, Int> {
    val charCount = sortedMapOf<Char, Int>()

    this.forEach {
        if (it.isLetterOrDigit()) {
            val count = charCount[it] ?: 0
            charCount[it] = count + 1
        }
    }

    return charCount
}*/
