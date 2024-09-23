package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.utils.JooqTable

class FeedbackTable(tableName: String = "feedback") : JooqTable(tableName) {
    val id = createBigIntField("id")
    val employeeId = createBigIntField("employee_id")
    val content = createVarcharField("content")
    val status = createVarcharField("status")
    val companyId = createBigIntField("company_id")
    val date = createLocalDateField("date")

    companion object {
        val instance = FeedbackTable()
    }
}
