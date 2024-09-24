package com.hibob.feedback.dao

import java.sql.Date
import java.util.*

data class Feedback(
    val id: UUID,
    val creationDate: Date,
    val companyId: UUID,
    val feedbackMessage: String,
    val employeeId: UUID?
)

data class FeedbackInput(
    val feedbackMessage: String,
    val isAnonymous: Boolean
)

data class ActiveUser(
    val employeeId: UUID,
    val companyId: UUID
)

enum class Department{
    RD,
    IT,
    HR,
    SALES,
    PRODUCT,
    FINANCE
}

enum class Status{
    REVIEWED,
    UNREVIEWED
}