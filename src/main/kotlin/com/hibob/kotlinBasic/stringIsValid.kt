package com.hibob.kotlinBasic

fun main() {
    println(isValidIdentifier("name"))
    println(isValidIdentifier("_name"))
    println(isValidIdentifier("_12"))
    println(isValidIdentifier(""))
    println(isValidIdentifier("012"))
    println(isValidIdentifier("no$"))
}

fun isValidIdentifier(s: String): Boolean {
    return s.isNotEmpty() && !s[0].isDigit() && s.all { it == '_' || it.isLetterOrDigit() }
}