package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.utils.JooqTable

class FeedbackTable(tableName: String = "feedback") : JooqTable(tableName) {

    val id = createBigIntField("id")
    val feedback = createVarcharField("feedback")
    val employeeId = createBigIntField("employee_id")
    val companyId = createBigIntField("company_id")
    val date = createLocalDateField("date")
    val status = createVarcharField("status")

    companion object {
        val instance = FeedbackTable()
    }
}


class EmployeeTable(tableName: String = "employees") : JooqTable(tableName) {

    val id = createBigIntField("id")
    val firstName = createVarcharField("first_name")
    val lastName = createVarcharField("last_name")
    val role = createVarcharField("role")
    val companyId = createBigIntField("company_id")
    val department = createVarcharField("department")

    companion object {
        val instance = EmployeeTable()
    }
}

class ResponseTable(tableName: String = "response") : JooqTable(tableName) {

    val id = createBigIntField("id")
    val feedbackId = createBigIntField("feedback_id")
    val response = createVarcharField("response")
    val employeeId = createBigIntField("employee_id")
    val date = createLocalDateField("date")

    companion object {
        val instance = ResponseTable()
    }
}