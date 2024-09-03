package com.hibob.kotlinEx

//change the 'sum\ function so that it was declared as an extension to List<Int>
fun sum(list: List<Int>): Int{
    var result = 0
    for (i in list){
        result +=i
    }
    return result
}

fun List<Int>.sum(): Int {
    var result = 0
    for (i in this) {
        result += i
    }
    return result
}

//write the following program method as an infix extension function
infix fun Number.toPowerOf(num: Number, exponent: Number): Double {
    var result: Double = 1
    for (i in 0 until exponent) {
        result = result*num
    }
    return result
}


fun main(args: Array<String>) {

    val sum = listOf(1, 2, 3).sum()
    println(sum)
}