package com.hibob.kotlion_basics

import kotlin.math.pow

fun List<Int>.sum(): Int {
    var result = 0
    map { result += it }
    return result
}

fun Number.toPowerOf(num: Double, exponent: Double): Double {
    return num.pow(exponent)
}

fun main() {
    val sum = listOf(1,2,3).sum()
    val num: Number = 2
    println(sum)
    println(num.toPowerOf(2.0, 3.0))
}