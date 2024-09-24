package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.utils.JooqTable
import com.sun.java.swing.ui.CommonUI.createTextField

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

class EmployeeTable(tableName: String = "employee") : JooqTable(tableName) {
    val id = createBigIntField("id")
    val firstName = createVarcharField("first_name")
    val lastName = createVarcharField("last_name")
    val rol = createVarcharField("role")
    val companyId = createBigIntField("company_id")

    companion object {
        val instance = EmployeeTable()
    }
}