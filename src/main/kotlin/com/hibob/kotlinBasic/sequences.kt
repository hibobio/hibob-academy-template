package com.hibob.kotlinBasic

import com.hibob.extension.toPowerOf
import kotlin.system.exitProcess

/*fun ex1() {
    // change the program to
    // 1. reuse the filter / map function
    // 2. println each call to track the diffs between List and Seq

    val list = listOf(1, 2, 3, 4)

    fun isEven(list: List<Int>): Boolean {}

    val maxOddSquare = list
        .map { it * it }
        .filter { it % 2 == 0 }
        .find { it == 4 }

    val maxOddSquare2 = list.asSequence()
        .map { it * it }
        .filter { it % 2 == 0 }
        .find { it == 4 }


    fun mapFilter(list: List<Int>): List<Int> {
        return list.map { it * it }.filter { it % 2 == 0 }
    }

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
    val toFi = generateSequence(2) { it * 2 }.take(10).toList()
    println(toFi)
     // create the list of the first 10 items of (2, 4, 8, 16, 32 ...) seq
}


fun ex4() {
    fibonacciSequence().take(10).forEach(::println)
    // create the list of the first 10 items of the Fibonacci seq
}

fun fibonacciSequence(): Sequence<Int> = sequence {
    var a = 0
    yield(a)
    var b = 1
    yield(b)

    while (true) {
        val next = a + b
        yield(next)
        a = b
        b = next
    }
}

 */


fun ex5() {
    // try to minimize the number of operations:

    val engToHeb: Map<String, String> = mapOf() // assume the dictionary is real :-)
    val b = "today was a good day for walking in the part. Sun was shining and birds were chirping"
        .split(" ")
        .mapNotNull {engToHeb[it]}
        .filter {it.length <= 3 }
        .size > 4
}

