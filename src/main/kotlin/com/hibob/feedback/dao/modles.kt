package com.hibob.feedback.dao

import java.sql.Date
import java.util.*

data class Feedback(
    val id: UUID,
    val creationDate: Date,
    val companyId: UUID,
    val feedbackMessage: String,
    val employeeId: UUID? = null
)

data class FeedbackInput(
    val feedbackMessage: String,
    val isAnonymous: Boolean
)

data class ActiveUser(
    val employeeId: UUID,
    val companyId: UUID
)

data class Employee(
    val employeeId: UUID,
    val companyId : UUID,
    val role: Role,
    val department: Department
)

enum class Department{
    RD,
    IT,
    HR,
    SALES,
    PRODUCT,
    FINANCE
}

enum class Role{
    ADMIN,
    MANAGER,
    EMPLOYEE
}

enum class Status{
    REVIEWED,
    UNREVIEWED
}