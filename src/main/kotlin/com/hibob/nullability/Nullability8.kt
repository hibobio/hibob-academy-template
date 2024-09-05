package com.hibob.nullability
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

    // Task: Print detailed information about each department, team, and team members, handling all null values appropriately.
    printCompany(company)
}

fun printCompany(company: Company) {
    val companyName = company.name ?: "NO COMPANY NAME"
    println("Company: $companyName")
    val departments = company.departments?.forEach { departmentDetails ->
        val departmentName = departmentDetails?.name ?: "NO DEPARTMENT NAME"
        val teams = departmentDetails?.teams?.let { it.map { team -> getTeam(team) } } ?: "NO TEAMS"
        "Department: $departmentName, Teams: $teams"
    } ?: println("NO DEPARTMENT")
    println("Departments: $departments")
}

fun getTeam(team: Team?): String {
    return team?.let {
        val name = it.name ?: "NO TEAM NAME"
        val leader = it.leader?.let {getLeader(team) } ?: "NO LEADER"
        val members = it.members?.let { memberList -> memberList.map { member -> getMember(member) } } ?: "NO MEMBERS"
        "Team: $name, $leader, $members" } ?: "NO TEAM"
}

fun getLeader(team: Team?): String {
    return team.let {
        val leaderName = it?.leader?.name ?: "NO LEADER NAME"
        val title = it?.leader?.title ?: "NO TITLE"
        "Leader: $leaderName, $title"
    }
}

fun getMember(member: Member?): String {
    return member?.let {
        val memberName = member.name ?: "NO MEMBER NAME"
        val memberRole = member.role ?: "NO ROLE"
        "Member: $memberName, $memberRole" } ?: "NO MEMBER"
}

