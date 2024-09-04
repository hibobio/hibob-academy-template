package com.hibob.kotlin_basics
//Write a “hello world” program
//Write a function that prints if the given number is even or odd
//Write a function that checks whether two integers are equal or not
//Write a function as an expression (one line) that prints the max value of two given integers
//Write as many possibilities as you can to call
//fun multiplication(a: Int = 1, b: Int = 1): Int = a * b
//with parameters
//a = 1, b= 5
//a = 1, b= 1
//a=3, b= 2

fun main() {
    // 1
    println("Hello World")

    // 2
    println("Even or odd: " + evenOrOdd(1))
    println("Even or odd: " + evenOrOdd(2))

    // 3
    println("Are equals: " + areNumbersEquals(1,2))
    println("Are equals: " + areNumbersEquals(2,2))

    // 4
    print("Max: ")
    printMaxValue(11,10)

    // 5
    println("Multiplication: " + multiplication(1,5))
    // a = 5 b = 1
    println("Multiplication: " + multiplication(5))
    println("Multiplication: " + multiplication(1,1))
    // a = 1 b = 1
    println("Multiplication: " + multiplication())
    println("Multiplication: " + multiplication(3,2))
}

fun evenOrOdd(num : Int) : String {
    if (num % 2 == 0) {
        return "Even"
    }
    return "Odd"
}
fun areNumbersEquals(n1: Int, n2: Int) : String {
    if (n1 == n2)
        return "Equal"
    return "Not Equal"
}
fun printMaxValue(num1: Int, num2: Int) = if (num1 > num2) println(num1) else println(num2)

fun multiplication(a: Int=1, b: Int=1): Int = a * b