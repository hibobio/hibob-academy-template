package com.hibob.kotlinBasics

// Exercise 1

// change the 'sum' function so that it was declared as an extenion to List<int>
// fun sum(list: List<int>): int {
//       var result = 0
//       for(i in list){
//           result += i
//       }
//       return result
//  }
//
//  fun main(args: Array<string>) {
//      val sum = sum(listOf(1,2,3))
//      print(sun) // 6
//  }

fun List<Int>.sum() : Int {
    var result = 0
    for(i in this){
        result += i
    }
    return result
}


// Exercise 2

// Write the following program method as an infix extension function
// fun toPowerOf(num: Number, exponent: Number): Double{
//      return 0
// }

infix fun Double.toPowerOf(exponent: Int): Double {
    var result = 1.0
    var negative = false
    var exponentToPower = exponent

    if(exponent < 0){
        negative = true
        exponentToPower = exponent * (-1)
    }

    for(e in 1 .. exponentToPower) {
        result *= this
    }

    if(negative)
        return 1 / result

    return result
}

fun main(args: Array<String>) {
    // exercise 1
    val list = listOf(1,2,3)
    val sum = list.sum()
    println(sum) // expected 6

    // exercise 2
    val powerOf = 2.0.toPowerOf(3)
    println(powerOf) // expected 8
}