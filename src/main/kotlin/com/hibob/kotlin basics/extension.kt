fun sum(list: List<Int>): Int {
    var result = 0
    for (i in list) {
        result += i
    }
    return result
}

infix fun Number.toPowerOf(num: Number, exponent: Number): Double {
    var result = 1;
    for (i in 1..exponent)
        result *= num
    return result
}

fun main(args: Array<String>) {
    val sum = sum(listOf(1,2,3))
    println(sum)
    fun List.sum(list: List<Int>): Int {
        var result = 0
        for (i in list)
            result += i
    }
    return result
}