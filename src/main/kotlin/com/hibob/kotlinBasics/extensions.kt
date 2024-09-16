package com.hibob.kotlinBasics

import kotlin.math.pow

fun List<Number>.sum(): Double {
    var sum = 0.0
    for(element in this){
        sum += element.toDouble() }
    return sum
}


infix fun Number.toPowerOf(exponent: Number):Double = this.toDouble().pow(exponent.toDouble())
