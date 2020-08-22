package com.tuliomagalhaes.palindrome

/*fun String.palindrome(): Boolean {
    return this.reversed() == this
}*/

fun String.palindrome(): Boolean {
    for(i in this.indices) {
        val lastIndex = (this.length - 1) - i
        if (this[i] != this[lastIndex]) {
            return false
        }
    }

    return true
}