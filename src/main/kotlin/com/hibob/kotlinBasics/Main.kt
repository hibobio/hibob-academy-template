//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    fun helloWorld() = println("Hello World!")

    fun evenOrOdd(num: Int) = if (num % 2 == 0) println("Even") else println("Odd")

    fun isEquals(a: Int, b: Int): Boolean = a == b

    fun printMax(a: Int, b: Int) = println(if (a > b) a else b)

    fun multiplication(a: Int = 1, b: Int = 1): Int = a * b

    multiplication(a = 1, b = 5)
    multiplication(b = 5)

    multiplication()
    multiplication(a = 1, b = 1)
    multiplication(a = 1)
    multiplication(b = 1)

    multiplication(3, 2)
    multiplication(a = 3, b = 2)
}