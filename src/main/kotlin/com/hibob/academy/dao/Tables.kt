package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable

class OwnerTable(tableName: String = "owner") : JooqTable(tableName){

    val name = createVarcharField("name")
    val companyId =createBigIntField("company_id")
    val employeeId = createVarcharField("employee_id")

    companion object {
        val instance = OwnerTable()
    }

}


class PetTable(tableName: String = "pets") : JooqTable(tableName){

    val name = createVarcharField("name")
    val type = createVarcharField("type")
    val companyId = createBigIntField("company_id")
    val dateOfArrival= createVarcharField("date_of_arrival")

    companion object {
        val instance = PetTable()
    }

}