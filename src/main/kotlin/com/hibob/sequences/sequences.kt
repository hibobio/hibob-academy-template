package com.hibob.sequences

fun ex1() {
    // change the program to
    // 1. reuse the filter / map function
    // 2. println each call to track the diffs between List and Seq
    val list = listOf(1, 2, 3, 4)

    fun isEven(x: Int) = x % 2 == 0
    fun square(x: Int) = x * x

    val maxOddSquare = list
        .map {
            println("Squaring $it")
            square(it) }
        .filter {
            println("Filtering $it")
            isEven(it) }
        .find { it == 4 }

    val maxOddSquare2 = list.asSequence()
        .map {
            println("Squaring $it")
            square(it) }
        .filter {
            println("Filtering $it")
            isEven(it) }
        .find { it == 4 }
}

fun ex2() {
    // how many times filterFunc was called
    fun filterFunc(it: Int): Boolean {
        println("filterFunc was called")
        return it < 3
    }
    sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        // before adding "forEach" the number of times filterFunc was called is 0
        .filter { filterFunc(it) }.forEach (::println)
}

fun ex3() {
    // create the list of the first 10 items of (2, 4, 8, 16, 32 ...) seq
    val squareNumbers = generateSequence(2){ it * 2 }.take(10).toList()
    println("Square numbers: ${squareNumbers}")

    fun crazySeq(): Sequence<Int> = sequence {
        var a = 2
        yield(a)

        while (true) {
            a *= 2
            yield(a)
        }
    }
    println("CrazySeq: ${crazySeq().take(10).toList()}")
}

fun ex4() {
    // create the list of the first 10 items of the Fibonacci seq
    val fibSeq = generateSequence(Pair(0, 1)) { Pair(it.second, it.first + it.second) }.map { it.first }.take(12).toList()
    println("Fib seq: ${fibSeq}")
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
        "the" to "ה",
        "park" to "פארק",
        "sun" to "שמש",
        "was" to "הייתה",
        "shining" to "זורחת",
        "and" to "ו",
        "birds" to "ציפורים",
        "were" to "היו",
        "chirping" to "מצייצות",
    )

    val str = "today was a good day for walking in the part. Sun was shining and birds were chirping"
    var count = 1
    val b = str
        .splitToSequence(" ")
        .mapNotNull {engToHeb[it]}
        .filter {
            println("Filtering $it count: ${count++}")
            it.length <= 3 }
        .take(5)
        .count() > 5

    println(b)
}

fun main() {
    ex1()
    ex2()
    ex3()
    ex4()
    ex5()
}