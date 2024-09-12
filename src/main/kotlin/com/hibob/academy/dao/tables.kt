package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable

class Pets(tableName: String = "pets") : JooqTable(tableName) {
    val ownerId = createBigIntField("owner_id")
    val petId = createBigIntField("id")
    val name = createVarcharField("name")
    val type = createVarcharField("type")
    val companyId = createBigIntField("company_id")
    val dateOfArrival = createLocalDateField("date_of_arrival")

    companion object {
        val instance = Pets()
    }
}

class Owner(tableName: String = "owner") : JooqTable(tableName) {
    val ownerId = createBigIntField("id")
    val name = createVarcharField("name")
    val companyId = createBigIntField("company_id")
    val employeeId = createVarcharField("employee_id")

    companion object {
        val instance = Owner()
    }
}