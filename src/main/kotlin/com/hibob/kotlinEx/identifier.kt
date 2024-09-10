package com.hibob.kotlinEx

fun isValidIdentifier(s: String): Boolean {
    if (s.isEmpty() || isDigit(s[0]) || !(isLetter(s[0]) || s[0] == '_')) {
        return false
    }
    for (i in 1 until s.length) {
        val c = s[i]
        if (!(isDigit(c) || isLetter(c)|| s[0] == '_')) {
            return false
        }
    }
    return true
}

private fun isLetter(c: Char): Boolean {
    return c in 'a'..'z' || c in 'A'..'Z'
}

private fun isDigit(c: Char): Boolean {
    return c in '0'..'9'
}

fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}