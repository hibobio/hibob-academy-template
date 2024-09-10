package com.hibob.kotlinBasics.nullability

/**
 * Write an extension function nullSafeToUpper() for String? that converts the string
 * to uppercase if it is not null, or returns "NO TEXT PROVIDED" if it is null.
 * Apply this function in the main function to handle the variable text.
 *
 **/
fun main() {
    fun String?.nullSafeToUpper() = this?.uppercase() ?: "NO TEXT PROVIDED"
    val text = "Learn Kotlin"
    // Task: Create and use an extension function to print text in uppercase if it's not null, or "NO TEXT PROVIDED" if it is null.
    val text2: String? = null
    println(text.nullSafeToUpper())
    println(text2.nullSafeToUpper())
}
