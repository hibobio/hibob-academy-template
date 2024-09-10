package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable
import javassist.CtMethod.ConstParameter.integer

class petTable(tableName: String = "pet") : JooqTable(tableName) {
    val id = createIntField("id")
    val name = createVarcharField("name")
    val type = createVarcharField("type")
    val company_id = createBigIntField("company_id")
    val date_of_arrival = createDateField("date_of_arrival")
}

class ownerTable(tableName: String = "owner") : JooqTable(tableName) {
    val id = createIntField("id")
    val name = createVarcharField("name")
    val company_id = createBigIntField("company_id")
    val employee_id = createVarcharField("employee_id")
}