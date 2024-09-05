package com.hibob.nullability

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

    // Implement the features here.
    val filterDepartments = filterDepartments(departments)
    //filterDepartments.forEach { println(it) }

    val uniqueManagerList = generateUniqueManagerList(filterDepartments)
    uniqueManagerList.forEach { println(it) }

    printDepartmentsDetails(filterDepartments)
}

fun filterDepartments(departments: List<DepartmentData>) :List<DepartmentData> {
    return departments.filter { departmentData ->
        val manager = departmentData.manager
        val contact = manager?.contactInfo

        if (manager == null || contact == null) false else true
    }
}

fun generateUniqueManagerList(departments: List<DepartmentData>): List<EmployeeData> {
    val uniqueManagerList = mutableListOf<EmployeeData>()
    departments.forEach { departmentData ->
        departmentData.manager.let { manager ->
            if (manager?.contactInfo?.email?.isNotEmpty() == true && manager.contactInfo?.email != null)
                uniqueManagerList.add(manager)
        }
    }
    return uniqueManagerList
}

fun printDepartmentsDetails(departments: List<DepartmentData>?) {
    departments?.let{ departmentList ->
        departmentList.forEach {
            val departmentName = it.name ?: "NO DEPARTMENT"
            val manager = it.manager?.let {
                val managerName = it.name ?: "NO EMPLOYEE"
                val contactInfo = it.contactInfo?.let {
                    val email = it.email ?: "NO EMAIL"
                    val phone = it.phone ?: "NO PHONE"
                    "EMAIL: $email, PHONE $phone"
                } ?: "NO CONTACT INFO"
                "$managerName, CONTACT: $contactInfo"
            } ?: "NO MANAGER"
            println("DEPARTMENT: $departmentName, MANAGER: $manager")
        }
    } ?: "NO DEPARTMENT"
}

