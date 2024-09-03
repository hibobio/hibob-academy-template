package com.hibob

fun printEvenOrOdd (num: Int) {
    if (num % 2 == 0) {
        println("even")
    }
    else println("odd")
}

fun isEqual(num1: Int, num2: Int) {
    if (num1 == num2) {
        println("equal")
    }
    else println("not equal")
}

fun oneLineBiggest (num1: Int, num2: Int) = if (num1 > num2) num1 else num2

fun multiplication(a: Int = 1, b: Int = 1): Int = a * b

fun main() {
    println("Hello world!")

    // test:
    com.hibob.printEvenOrOdd(1)
    com.hibob.printEvenOrOdd(2)

    com.hibob.isEqual(2, 3)
    com.hibob.isEqual(2, 2)

    println(com.hibob.oneLineBiggest(1, 2))
    println(com.hibob.oneLineBiggest(2, 1))

    println(com.hibob.multiplication())
    println(com.hibob.multiplication(a = 2))
    println(com.hibob.multiplication(b = 2))
    println(com.hibob.multiplication(a = 3, b = 2))
    println(com.hibob.multiplication(b = 3, a = 2))
}