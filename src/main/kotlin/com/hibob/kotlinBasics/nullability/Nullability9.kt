package com.hibob.kotlinBasics.nullability

/**
 * Filter Departments: Identify departments that have either no manager assigned or where the manager's
 * contact information is entirely missing.
 *
 * Email List Compilation: Generate a list of all unique manager emails but exclude any null
 * or empty strings. Ensure the list has no duplicates.
 *
 * Reporting: For each department, generate a detailed report that includes the
 * department name, manager's name, email, and formatted phone number.
 * If any information is missing, provide a placeholder.
 *
 */

data class DepartmentData(val name: String?, val manager: EmployeeData?)
data class EmployeeData(val name: String?, val contactInfo: Contact?)
data class Contact(val email: String?, val phone: String?)

fun main() {
    val departments = listOf(
        DepartmentData("Engineering", EmployeeData("Alice", Contact("alice@example.com", "123-456-7890"))),
        DepartmentData("Human Resources", null),
        DepartmentData("Operations", EmployeeData("Bob", Contact(null, "234-567-8901"))),
        DepartmentData("Marketing", EmployeeData(null, Contact("marketing@example.com", "345-678-9012"))),
        DepartmentData("Finance", EmployeeData("Carol", Contact("", "456-789-0123")))

    )

    fun noContactInfo(contact: Contact?): Boolean = contact == null || (contact.email == null && contact.phone == null)

    fun noManagerDepartments(): List<DepartmentData> {
        return departments.filter { it.manager == null || noContactInfo(it.manager.contactInfo) }
    }

    fun uniqueEmails(): List<String> {
        val emails: List<String> = departments.filter { (it.manager?.contactInfo?.email != "") }
            .map { it.manager?.contactInfo?.email ?: "" }.filter { it != "" }

        return emails.toSet().toList()
    }

    fun Reporting(): String {
        return buildString { departments.forEach { appendLine("The department name is ${it.name ?: "Unknown"} and the manager name is ${it.manager?.name ?: "Unknown"}, his email is ${it.manager?.contactInfo?.email ?: "Unknown"} and his phone is ${it.manager?.contactInfo?.phone ?: "Unknown"}") } }
    }
}