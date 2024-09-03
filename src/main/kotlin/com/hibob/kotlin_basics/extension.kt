package com.hibob.kotlion_basics

import kotlin.math.pow

fun List<Int>.sum(): Int {
    var result = 0
    for (i in this)
        result += i
    return result
}

fun Number.toPowerOf(num: Double, exponent: Double): Double {
    return num.pow(exponent)
}

fun main(args: Array<String>) {
    val sum = listOf(1,2,3).sum()
    val num = sum.toPowerOf(2.0, 3.0)
    println(sum)
    println(num)
}