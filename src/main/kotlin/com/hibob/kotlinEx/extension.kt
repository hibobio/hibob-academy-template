package com.hibob.kotlinEx
import kotlin.math.pow

fun List<Int>.sum(): Int {
    var result = 0
    for (i in this) {
        result += i
    }
    return result
}

infix fun Number.toPowerOf(exponent: Number): Double {
    return this.toDouble().pow(exponent.toDouble())
}


fun main(args: Array<String>) {

    val sum = listOf(1, 2, 3).sum()
    val x: Number = 2
    println(sum)
    println(x.toPowerOf(2))
}