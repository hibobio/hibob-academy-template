package com.hibob.kotlinEx

import kotlin.system.exitProcess


fun ex1() {
    // change the program to
    // 1. reuse the filter / map function
    // 2. println each call to track the diffs between List and Seq
    fun isEven(n: Int): Boolean {
        println("${n % 2 == 0}")
        return n % 2 == 0
    }

    fun square(n: Int): Int {
        println("${n * n}")
        return n * n
    }

    val list = listOf(1, 2, 3, 4)

    val maxOddSquare = list
        .map { square(it) }
        .filter { isEven(it) }
        .find { it == 4 }

    val maxOddSquare2 = list.asSequence()
        .map { square(it) }
        .filter { isEven(it) }
        .find { it == 4 }
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
    // create the list of the first 10 items of (2, 4, 8, 16, 32 ...) seq
    generateSequence (1){it*2}.take(10).forEach { println(it) }
}

fun ex4() {
    // create the list of the first 10 items of the Fibonacci seq
    generateSequence (Pair(0,1)){pair -> Pair(pair.second, pair.first+pair.second) }.take(10).forEach { println(it.first) }
}

fun ex5() {
    // try to minimize the number of operations:

    val engToHeb: Map<String, String> = mapOf("today" to "היום", "was" to "היה", "good" to "טוב", "day" to "יום") // assume the dictionary is real :-)
    val b = "today was a good day for walking in the part. Sun was shining and birds were chirping"
        .splitToSequence(" ")
        .mapNotNull { engToHeb[it] }
        .filter { println("processing $it")
            it.length <= 3 }
        .take(5)
        .count()>4
}


public fun main() {
//    ex1()
//    ex3()
//    ex4()
    ex5()
}