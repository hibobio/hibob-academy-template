package com.hibob.kotlinBasics

fun main(args: Array<String>) {
    ex1()
    ex2()
    ex3()
    ex4()
    ex5()
}

fun ex1() {
    // change the program to
    // 1. reuse the filter / map function
    // 2. println each call to track the diffs between List and Seq

    val list = listOf(1, 2, 3, 4)
    fun isEven(num: Int): Boolean = num % 2 == 0
    fun sq(num: Int): Int = (num * num)

    val maxOddSquare = list
        .map {
            println("squaring $it")
            sq(it)
        }
        .filter {
            println("Filtering $it")
            isEven(it)
        }
        .find { it == 4 }

    val maxOddSquare2 = list.asSequence()
        .map {
            println("squaring $it")

            sq(it)
        }
        .filter {
            println("Filtering $it")
            isEven(it)
        }
        .find { it == 4 }


}


fun ex2() {
    // how many times filterFunc was called = 0
    fun filterFunc(it: Int): Boolean {
        println("filterFunc was called")
        return it < 3
    }
    sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .filter { filterFunc(it) }
}

fun ex3() {
    // create the list of the first 10 items of (2, 4, 8, 16, 32 ...) seq
    val powerOf2 = generateSequence(1, { it * 2 }).take(10).toList()
    println(powerOf2)
}

fun ex4() {
    // create the list of the first 10 items of the Fibonacci seq
    fun fibSeq(): Sequence<Int> = sequence {
        var prev = 1
        var curr = 0
        yield(curr)

        while (true) {
            val temp = curr
            curr += prev
            prev = temp
            yield(curr)
        }
    }

    val fib = fibSeq().take(10).toList()
    println(fib)
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
        "chirping" to "מצייצות"
    ) // assume the dictionary is real :-)
    val b = "today was a good day for walking in the part. Sun was shining and birds were chirping"
        .split(" ")
        .mapNotNull { engToHeb[it] }
        .filter {
            it.length <= 3
        }
        .size > 4
    println("today was a good day for walking in the part. Sun was shining and birds were chirping"
        .splitToSequence(" ").mapNotNull { engToHeb[it] }.filter{
            it.length <= 3
        }.take(5).count() > 4
    )
}