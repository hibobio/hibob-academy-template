package com.hibob.bootcamp

import java.util.*


/**
 * Write an extension function nullSafeToUpper() for String? that converts the string
 * to uppercase if it is not null, or returns "NO TEXT PROVIDED" if it is null.
 * Apply this function in the main function to handle the variable text.
 *
 **/
fun main() {
    val text: String? = "Learn Kotlin"
    // Task: Create and use an extension function to print text in uppercase if it's not null, or "NO TEXT PROVIDED" if it is null.

    text?.let {
        println(text.toUpperCase(Locale.getDefault()))
    }?: println("NO TEXT PROVIDED")
}

