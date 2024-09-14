package com.hibob.kotlinBasic

fun main() {
    val x = 2
    val y = 3

    println("Hello world")

    fun printsEvenOrOdd(x: Int) = println(if (x > 0) "even" else "odd")
    printsEvenOrOdd(x = 2)
    printsEvenOrOdd(x = 3)

    fun checkEqual(x: Int, y: Int) = println(if (x == y) "equal" else "not equal")
    checkEqual(x, y)

    fun maxValue(x: Int, y: Int) = println(if(x > y) x else y)
    maxValue(x, y)

    fun multiplication(x: Int = 1, y: Int = 1): Int = x * y

    val a1 = 1
    val b1 = 5
    println(multiplication(b1))
    println(multiplication(a1,b1))

    val a2 = 1
    val b2 = 1
    println(multiplication())
    println(multiplication(a2))
    println(multiplication(b2))
    println(multiplication(a2, b2))

    val a3 = 3
    val b3 = 2
    println(multiplication(a3, b3))
}