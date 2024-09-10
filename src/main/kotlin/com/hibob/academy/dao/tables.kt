package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable
import javassist.CtMethod.ConstParameter.integer
import java.io.Serial

class PetsTable(tableName : String = "pets") : JooqTable(tableName) {
    val id = createIntField("id")
    val name = createVarcharField("name")
    val type = createVarcharField("type")
    val companyId = createBigIntField("company_Id")
    val date_of_arrival = createDateField("date_of_arrival")


    companion object{
        val instance = PetsTable()
    }
}

class OwnersTable(tableName : String = "owners") : JooqTable(tableName) {

    val id = createIntField("id")
    val name = createVarcharField("name")
    val companyId = createBigIntField("company_id")
    val employeeId = createIntField("employee_id")

    companion object{
        val instance = OwnersTable()
    }
}