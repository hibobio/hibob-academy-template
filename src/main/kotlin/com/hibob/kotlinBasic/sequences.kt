package com.hibob.kotlinexercise

import kotlin.system.exitProcess
fun main() {
    println("ex1")
    ex1()
    println()

    println("ex2")
    ex2()
    println()

    println("ex3")
    ex3()
    println()

    println("ex4")
    ex4()
    println()

    println("ex5")
    ex5()
    println()

}

fun ex1() {
    // change the program to
    // 1. reuse the filter / map function
    // 2. println each call to track the diffs between List and Seq

    val list = listOf(1, 2, 3, 4)

    fun isEven(i: Int) = i % 2 == 0

    fun sq(i: Int) = i * i

    val maxOddSquare = list
        .map {
            println("Squaring $it")
            sq(it)
        }
        .filter {
            println("Filtering $it")
            isEven(it)
        }
        .find { it == 4 }

    val maxOddSquare2 = list
        .asSequence()
        .map {
            println("Squaring $it")
            sq(it)
        }
        .filter {
            println("Filtering $it")
            isEven(it)
        }
        .find { it == 4 }
    println("Found $maxOddSquare from list and $maxOddSquare2 from sequence")
}


fun ex2() {
    // how many times filterFunc was called
    fun filterFunc(it: Int): Boolean {
        println("filterFunc was called")
        return it < 3
    }
    sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .filter { filterFunc(it) }
}

fun ex3() {
    // op1
    val tenFirstItems1 = generateSequence(2) { it * 2 }.take(10).toList()
    println(tenFirstItems1)

    //op2
    fun crazySeq(): Sequence<Int> = sequence {
        var count = 1
        var a = 2
        println("Returing element ${count++}")
        yield(a)

        while (true) {
            a *= 2
            println("Returing element ${count++}")
            yield(a)
        }
    }
    val tenFirstItems2 = crazySeq().take(10).toList()
    println(tenFirstItems2)

    //op3
    val tenSecondItems3 = sequenceOf(2, 4, 8, 16, 32, 64, 128, 256, 512, 1024).toList()
    println(tenSecondItems3)

    // create the list of the first 10 items of (2, 4, 8, 16, 32 ...) seq
}

fun ex4() {

    fun fibonacciSequence(): Sequence<Int> = sequence {
        var a = 1
        var b = 2
        yield(a)
        yield(b)

        while (true) {
            val next = a + b
            yield(next)
            a = b
            b = next
        }
    }

    val fibonacciSequence = fibonacciSequence().take(10).forEach(::println)
    // create the list of the first 10 items of the Fibonacci seq
}

fun ex5() {
    // try to minimize the number of operations:

    val engToHeb: Map<String, String> = mapOf(
        "today" to "היום",
        "was" to "היה",
        "good" to "טוב",
        "day" to "יום",
        "for" to "בשביל",
        "walking" to "ללכת",
        "in" to "ב",
        "the" to "הבלבלה",
        "park" to "פארק",
        "sun" to "שמש",
        "was" to "הייתה",
        "shining" to "זורחת",
        "and" to "ו",
        "birds" to "ציפורים",
        "were" to "היו",
        "chirping" to "מצייצות",
    ) // assume the dictionary is real :-)
    val b = "today was a good day for walking in the park. Sun was shining and birds were chirping"
        .splitToSequence(" ")
        .mapNotNull {engToHeb[it]}
        .filter {
            val isValid = it.length <= 3
            if(isValid) println(it)
            isValid
        }
        .take(5)
        .count() > 4
    println(b)
}
