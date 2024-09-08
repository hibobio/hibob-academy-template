/**
 * Instructions:
 *
 * Traverse through the company structure starting from departments to teams and finally to team members.
 * For each level (company, department, team, leader, members), check for null values and print appropriate information.
 * Ensure that every piece of information printed includes a fallback for null values using the Elvis operator and that
 * blocks of code dependent on non-null values are executed using ?.let.
 *
 */
data class Company(val name: String?, val departments: List<DepartmentDetails?>?)
data class DepartmentDetails(val name: String?, val teams: List<Team?>?)
data class Team(val name: String?, val leader: Leader?, val members: List<Member?>?)
data class Leader(val name: String?, val title: String?)
data class Member(val name: String?, val role: String?)

fun initializeCompany(): Company {
    return Company(
        "Tech Innovations Inc.",
        listOf(
            DepartmentDetails("Engineering", listOf(
                Team("Development", Leader("Alice Johnson", "Senior Engineer"), listOf(Member("Bob Smith", "Developer"), null)),
                Team("QA", Leader(null, "Head of QA"), listOf(Member(null, "QA Analyst"), Member("Eve Davis", null))),
                null
            )),
            DepartmentDetails(null, listOf(
                Team("Operations", null, listOf(Member("John Doe", "Operator"), Member("Jane Roe", "Supervisor")))
            )),
            null
        )
    )
}

fun main() {
    val company = initializeCompany()

    println("Company name: ${company.name ?: "Unknown company"}")
    println()

    company.departments?.forEach { departmentDetails ->
        println("Department name: ${departmentDetails?.name ?: "Name unknown"}")

        departmentDetails?.teams?.forEach { teamDetails ->
            println("Team name: ${teamDetails?.name ?: "Name unknown"}")
            println("Team leader name: ${teamDetails?.leader?.name ?: "Name unknown"}, Team leader title: ${teamDetails?.leader?.title ?: "Title unknown"}")

            teamDetails?.members?.forEach { memberDetails ->
                println("Member name: ${memberDetails?.name ?: "Name unknown"}, Member role: ${memberDetails?.role ?: "Role unknown"}")
            }
            println()
        }
    }
    // Task: Print detailed information about each department, team, and team members, handling all null values appropriately.
}