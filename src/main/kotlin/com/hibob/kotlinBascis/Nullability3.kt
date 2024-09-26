package com.hibob.kotlinBascis

/**
 * Write an extension function nullSafeToUpper() for String? that converts the string
 * to uppercase if it is not null, or returns "NO TEXT PROVIDED" if it is null.
 * Apply this function in the main function to handle the variable text.
 *
 **/
fun String?.nullSafeToUpper():String = this?.uppercase()?: "NO TEXT PROVIDED"
fun main2() {
    val text: String? = "Learn Kotlin"
    println(text.nullSafeToUpper())
//     Task: Create and use an extension function to print text in uppercase if it's not null, or "NO TEXT PROVIDED" if it is null.
}

