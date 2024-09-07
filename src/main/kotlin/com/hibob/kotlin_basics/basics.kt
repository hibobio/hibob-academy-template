package com.hibob.kotlin_basics

fun main() {
    //Q1
    printHelloWorld()
    //Q2
    println(evenOrOdd(2))
    println(evenOrOdd(3))
    //Q3
    println(equalNumbers(2,2))
    println(equalNumbers(2,3))
    //Q4
    println(maxValue(11,10))
    //a=1, b =5 ===> 5
    println(multiplication(1,5))
    println(multiplication(5))
    //a=1, b = 1 ===> 1
    println(multiplication(1,1))
    println( multiplication())
    //a=3, b=2 ===> 6
    println(multiplication(3,2))
    println(multiplication(6))
}
fun printHelloWorld() {
    println("Hello World!")
}
fun evenOrOdd(num : Int) : String {
    if (num % 2 == 0) {
        return "Even"
    }
    return "Odd"
}
fun equalNumbers(num1: Int, num2: Int) : String {
    if (num1 == num2) {
        return "Equal"
    }
    return "Not Equal"
}
fun maxValue(num1: Int, num2: Int) : Int = if (num1 > num2) num1 else num2
fun multiplication(a: Int=1, b: Int=1): Int = a * b