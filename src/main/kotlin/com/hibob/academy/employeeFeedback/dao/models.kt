package com.hibob.academy.employeeFeedback.dao

import java.time.LocalDate

data class FeedbackIn(
    val employeeId: Long?,
    val content: String,
    val status: FeedbackStatus = FeedbackStatus.UNREVIEWED,
    val companyId: Long
)

data class FeedbackOut(
    val id: Long,
    val employeeId: Long?,
    val content: String,
    val status: FeedbackStatus,
    val companyId: Long,
    val date: LocalDate
)

data class EmployeeIn(
    val firstName: String,
    val lastName: String,
    val companyId: Long,
)

data class EmployeeOut(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val role: RoleType,
    val companyId: Long,
)

enum class RoleType {
    ADMIN,
    MANAGER,
    EMPLOYEE,
    HR;

    companion object {
        fun enumToString(role: RoleType): String =
            role.toString()

        fun stringToEnum(string: String): RoleType =
            valueOf(string.uppercase())
    }
}

enum class FeedbackStatus {
    REVIEWED,
    UNREVIEWED;

    companion object {
        fun enumToString(status: FeedbackStatus): String =
            status.toString()

        fun stringToEnum(string: String): FeedbackStatus =
            valueOf(string.uppercase())
    }
}