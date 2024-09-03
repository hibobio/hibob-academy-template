package com.hibob.kotlinEx

//Write a “hello world” program
fun main() {
    fun helloWorld() {
        println("Hello World!")
    }
    helloWorld()
    //Write a function that prints if the given number is even or odd
    fun isOdd(x: Int) = if (x%2==0) println("even") else println("odd")
    isOdd(42)
    //Write a function that checks whether two integers are equal or not
    fun isEqual(x: Int, y: Int):Boolean = x==y
    println(isEqual(42, 43).toString())
    //Write a function as an expression (one line) that prints the max value of two given integers
    fun printMax(x: Int, y: Int) = if (x>=y) println("$x") else println("$y")
    printMax(42, 43)
    //Write as many possibilities as you can to call
    fun multiplication(a: Int = 1, b: Int = 1): Int = a * b
    //with parameters
    //a = 1, b= 5
    val x1 = multiplication(b=5)
    val x2 = multiplication(a=1, b=5)
    //a = 1, b= 1
    val x3 = multiplication()
    val x4 = multiplication(a=1)
    val x5 = multiplication(b=1)
    //a=3, b= 2
    val x6 = multiplication(a=3, b=2)//
    //
}