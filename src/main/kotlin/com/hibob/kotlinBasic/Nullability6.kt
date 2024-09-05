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
    for (department in departments) {
        val departmentName = department.name ?: "Unknown Department"
        val departmentManager = department.manager?.name ?: "Unknown manager name"
        val contactEmailManager = department.manager?.contactInfo?.email ?: "Unknown manager email"
        val cotactPhoneManager = department.manager?.contactInfo?.phone ?: "Unknown manager phone"

        println("department name: $departmentName, Manager name: $departmentManager, Email address: $contactEmailManager, Phone number: $cotactPhoneManager" )
    }
    // Task: Print each department's name and manager's contact information.
    // If any information is missing, use appropriate defaults.
}