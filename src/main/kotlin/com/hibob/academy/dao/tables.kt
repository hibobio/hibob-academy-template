package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable

class Pets(tableName: String = "pets") : JooqTable(tableName) {
    val name = createVarcharField("name")
    val type = createVarcharField("type")
    val companyId = createBigIntField("company_id")
    val dateOfArrival = createDateField("dateOfArrival")

    companion object {
        val instance = Pets()
    }
}

class Owner(tableName: String = "owner") : JooqTable(tableName) {
    val name = createVarcharField("name")
    val companyId = createBigIntField("company_id")
    val employeeId = createBigIntField("employee_id")

    companion object {
        val instance = Owner()
    }
}

