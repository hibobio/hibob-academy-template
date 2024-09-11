package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable
import javassist.CtMethod.ConstParameter.integer

class PetTable(tableName: String = "pet") : JooqTable(tableName) {
    val id = createIntField("id")
    val name = createVarcharField("name")
    val type = createVarcharField("type")
    val companyId = createBigIntField("company_id")
    val dateOfArrival = createDateField("date_of_arrival")

    companion object {
        val instance = PetTable()
    }
}

class OwnerTable(tableName: String = "owner") : JooqTable(tableName) {
    val id = createIntField("id")
    val name = createVarcharField("name")
    val companyId = createBigIntField("company_id")
    val employeeId = createVarcharField("employee_id")

    companion object {
        val instance = OwnerTable()
    }
}