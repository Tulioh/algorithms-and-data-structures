package com.tuliomagalhaes.fizzbuzz

class Printer {
    fun print(value: String) {
        println(value)
    }
}

class FizzBuzzPrinter(
    private val printer: Printer = Printer()
) {
    fun fizzBuzz(n: Int) {
        for (i in 1..n) {
            when {
                i % 3 == 0 && i % 5 == 0 -> printer.print("fizzbuzz")
                i % 3 == 0 -> printer.print("fizz")
                i % 5 == 0 -> printer.print("buzz")
                else -> printer.print(i.toString())
            }
        }
    }
}