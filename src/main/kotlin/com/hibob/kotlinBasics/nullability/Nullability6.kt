data class Department(val name: String?, val manager: EmployeeDetails?)
data class EmployeeDetails(val name: String?, val contactInfo: ContactInfo?)
data class ContactInfo(val email: String?, val phone: String?)

fun main() {
    val departments = listOf(
        Department("Engineering", EmployeeDetails("Alice", ContactInfo("alice@example.com", null))),
        Department("Human Resources", null),
        Department(null, EmployeeDetails("Bob", ContactInfo(null, "123-456-7890"))),
        Department("Marketing", EmployeeDetails(null, ContactInfo("marketing@example.com", "987-654-3210")))
    )
    departments.forEach({element -> println("Department name is ${element.name ?: "Unknown"}, the Manager's " +
            "email is ${element.manager?.contactInfo?.email ?: "Unknown"} and phone is ${element.manager?.contactInfo?.phone ?:"Unknown"}")})

    // Task: Print each department's name and manager's contact information.
    // If any information is missing, use appropriate defaults.
}