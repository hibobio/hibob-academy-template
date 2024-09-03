package com.hibob.KotlinBasics
//Q1
fun List<Int>.sum():Int{
    var result = 0
    for (i in this){
        result+=i
    }
    return result
}

fun main(args:Array<String>){
    val sum = listOf(1,2,3).sum()
    println(sum) //6
}


//Q2
infix fun Number.toPowerOf(exponent: Number):Double{
    Math.pow(this.toDouble(), exponent.toDouble())
//  //Other solution:
//    val pow = 2
//    var retValue = this
//    if (exponent == 0)
//        return 1
//    repeat((exponent.toInt() - 1)) {
//        retValue *= this
//    }
//    if (exponent>=1)
//        return retValue
//    else
//        return 1/retValue
}