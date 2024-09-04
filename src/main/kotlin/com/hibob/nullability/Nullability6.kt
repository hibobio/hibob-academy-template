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
    printDepartmentAndContact(departments)

    // Task: Print each department's name and manager's contact information.
    // If any information is missing, use appropriate defaults.

}

fun printDepartmentAndContact(departments: List<Department>) {
    departments.forEach { department ->
        val name = department.name ?: "NO NAME"
        val manager = department.manager?.let {
            val email = department.manager.contactInfo?.email ?: "NO EMAIL"
            val phone = department.manager.contactInfo?.phone ?: "NO PHONE"
            "Email: $email\nPhone: $phone"
            } ?: "NO MANAGER"
        println("Name: ${name}\nContact Info:\n${manager}\n")
    }
}