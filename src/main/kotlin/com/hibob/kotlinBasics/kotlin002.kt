
    fun isValidIdentifier(s: String): Boolean {
        fun isValidChar(ch:Char) = (ch in 'a'..'z' || ch in 'A'..'Z' || ch in '0'..'9' || ch =='_')
        if(s.isEmpty() || s[0] in '0'..'9') return false
        for( char in s) {
            if(!isValidChar(char)) return false
        }
        return true
    }

    fun main(args: Array<String>) {
        println(isValidIdentifier("name"))   // true
        println(isValidIdentifier("_name"))  // true
        println(isValidIdentifier("_12"))    // true
        println(isValidIdentifier(""))       // false
        println(isValidIdentifier("012"))    // false
        println(isValidIdentifier("no$"))    // false
    }