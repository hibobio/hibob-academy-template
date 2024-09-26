package com.hibob.kotlinBasics

fun trs(){
    val m = listOf(1)
}

fun List<Int>.sum(): Int{
    var result=0
    for(i in this){
        result +=i
    }
    return result
}

infix fun Double.toPowerOf(exponent:Double): Double{
    return Math.pow(this,exponent)
}
