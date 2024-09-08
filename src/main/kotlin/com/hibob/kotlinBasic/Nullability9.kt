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

    val departmentWithNoNull = departments.filter {
        it.manager?.contactInfo?.let { info ->
            info.phone.isNullOrBlank() && info.email.isNullOrBlank()
        } ?: true
    }
    departmentWithNoNull.forEach {dept ->
        println(dept.name)
    }

    val uniqeManagerEmail = departments.mapNotNull { it.manager?.contactInfo?.email } .filter { it.isNotBlank() } .toSet()
    uniqeManagerEmail.forEach {
        println(it)
    }

    departments.forEach {
        println("Department name: ${it.name ?: "Unknown name"}," +
                " Manager name: ${it.manager?.name ?: "Unknown manager"}," +
                " Email: ${it.manager?.contactInfo?.email.takeIf { email -> !email.isNullOrEmpty() } ?: "Unknown Email"}," +
                " Phone: ${it.manager?.contactInfo?.phone ?: "Unknown Phone"} ")
    }
    // Implement the features here.
}