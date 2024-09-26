package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable


class OwnerTable(tableName: String = "owner") : JooqTable(tableName){

    val id = createBigIntField("id")
    val name = createVarcharField("name")
    val companyId =createBigIntField("company_id")
    val employeeId = createBigIntField("employee_id")

    companion object {
        val instance = OwnerTable()
    }

}


class PetTable(tableName: String = "pets") : JooqTable(tableName){

    val id = createBigIntField("id")
    val name = createVarcharField("name")
    val type = createVarcharField("type")
    val companyId = createBigIntField("company_id")
    val dateOfArrival= createLocalDateField("date_of_arrival")
    val ownerId = createBigIntField("owner_id")

    companion object {
        val instance = PetTable()
    }

}