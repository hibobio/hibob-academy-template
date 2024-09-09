package com.hibob.bootcamp

import jakarta.validation.constraints.Null


/**
 * Write an extension function nullSafeToUpper() for String? that converts the string
 * to uppercase if it is not null, or returns "NO TEXT PROVIDED" if it is null.
 * Apply this function in the main function to handle the variable text.
 *
 **/

fun String?.nullSafeToUpper() : String? {
    this?.let {
        return it.toUpperCase()
    } ?: return "NO TEXT PROVIDED"
}

fun main() {
    val text: String? = "Learn Kotlin"
    val nullText: String? = null
    println(text.nullSafeToUpper())
    println(nullText.nullSafeToUpper())
}

