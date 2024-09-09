package com.hibob.kotlin_basics

fun isValidIdentifier(s: String): Boolean {
    if (s.isNotEmpty() && (s[0] == '_' || s[0].isLetter()))
        return s.drop(1).all {
            it.isLetterOrDigit() || it == '_'
        }
    return false
}

fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}