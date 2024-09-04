package com.hibob.bootcamp

import com.hibob.kotlion_basics.sum


/**
* Modify the main function to calculate and print the sum of all non-null integers in the list numbers.
* Use safe calls and/or the Elvis operator where appropriate.
 **/
fun main() {
    val numbers: List<Int?> = listOf(1, null, 3, null, 5)
    val nonNull = numbers.filterNotNull().sum()
    println(nonNull)
    // Task: Calculate the sum of all non-null numbers
}
