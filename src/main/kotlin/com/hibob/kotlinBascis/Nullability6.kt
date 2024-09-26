data class Department(val name: String?, val manager: EmployeeDetails?)
data class EmployeeDetails(val name: String?, val contactInfo: ContactInfo?)
data class ContactInfo(val email: String?, val phone: String?)

fun main6() {
    val departments = listOf(
        Department("Engineering", EmployeeDetails("Alice", ContactInfo("alice@example.com", null))),
        Department("Human Resources", null),
        Department(null, EmployeeDetails("Bob", ContactInfo(null, "123-456-7890"))),
        Department("Marketing", EmployeeDetails(null, ContactInfo("marketing@example.com", "987-654-3210")))
    )

    // Task: Print each department's name and manager's contact information.
    // If any information is missing, use appropriate defaults.
    departments.forEach {department->println("The name is: ${department.name ?: "unknown"} The manager is: ${department.manager?.name ?: "unknown"} The email is:${department.manager?.contactInfo?.email ?: "unknown"} The phone is: ${department.manager?.contactInfo?.phone ?: "unknown"}")
    }
//    for (department in departments) {
//        println("The name is: ${department.name ?: "unknown"} The manager is: ${department.manager?.name ?: "unknown"} The email is:${department.manager?.contactInfo?.email ?: "unknown"} The phone is: ${department.manager?.contactInfo?.phone ?: "unknown"}")
//        }
    }
