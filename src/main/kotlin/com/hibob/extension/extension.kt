package com.hibob.extension

import kotlin.math.pow

fun List<Int>.sum(): Int {
    var sum = 0
    for (item in this) {
        sum += item
    }
    return sum
}

infix fun Number.toPowerOf(power: Double): Double {
    return this.toDouble().pow(power)
}

fun main(){
    val sum = listOf(1,2,3).sum()
    println(sum)
    val num = 3.0
    val res = num.toPowerOf(2.0)
    println(res)
}