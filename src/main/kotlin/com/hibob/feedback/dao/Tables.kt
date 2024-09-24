package com.hibob.feedback.dao


import com.hibob.academy.utils.JooqTable


class FeedbackTable(tablename: String) : JooqTable(tablename) {
    val feedbackId = createUUIDField("id")
    val employeeId = createUUIDField("employee_id")
    val creationDate = createDateField("creation_date")
    val companyId = createUUIDField("company_id")
    val status = createVarcharField("status")
    val feedbackMessage = createVarcharField("feedback_message")

    companion object {
        val instance = FeedbackTable("feedback")
    }
}

class CompanyTable(tablename: String) : JooqTable(tablename) {
    val companyId = createUUIDField("id")
    val name = createVarcharField("name")

    companion object {
        val instance = CompanyTable("company")
    }
}
