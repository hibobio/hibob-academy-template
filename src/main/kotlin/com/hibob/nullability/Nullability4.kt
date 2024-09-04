package com.hibob.bootcamp


/**
 * Modify the main function to iterate over the employees list and print each employee's city.
 * Use safe calls (?.) and the Elvis operator (?:) to ensure that if an employee or their address is null,
 * or if the city is null, "City Unknown" is printed instead.
 * Try to write the solution in the most concise way possible using Kotlin's chaining capabilities.
 *
 **/
data class Address(val city: String?, val street: String?)
data class Employee(val name: String?, val address: Address?)

fun main() {
    val employees = listOf(
        Employee("John", Address("New York", "Fifth Ave")),
        Employee("Jane", null),
        Employee(null, Address(null, "Unknown Street")),
        Employee("Alice", Address("Los Angeles", null))
    )
    printEmployeeCitySafely(employees)

    // Task: Print each employee's city safely. If the city is not available, print "City Unknown".
}

fun printEmployeeCitySafely(employees: List<Employee>) {
    employees.forEach { employee ->
        employee.address?.city?.let {
            println("${employee.name}, ${employee.address.city}")
        }
    }
}

