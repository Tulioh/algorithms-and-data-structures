package com.tuliomagalhaes.reverseint

/*
fun Int.reverseInt(): Int {
    var reversed = this.toString().reversed()
    if (reversed.last() == '-') {
        reversed = "-${reversed.substring(0, reversed.length - 1)}"
    }
    return reversed.toInt()
}
*/

fun Int.reverseInt(): Int {
    val reversed = this.toString().reversed()
    if (this < 0) {
        return reversed.dropLast(1).toInt() * -1
    }
    return reversed.toInt()
}