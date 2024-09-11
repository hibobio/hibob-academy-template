package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable
import javassist.CtMethod.ConstParameter.integer

class PetsTable(tablename: String): JooqTable(tablename) {
    val id = createUUIDField("id")
    val name = createVarcharField("name")
    val type = createVarcharField("type")
    val companyId = createBigIntField("company_id")
    val dateOfArrivel = createDateField("date_of_arrivel")
    val ownersId = createUUIDField("owners_id")

    companion object{
        val instance = PetsTable("pets")
    }
}


class OwnersTable(tablename: String): JooqTable(tablename) {
    val id = createUUIDField("id")
    val companyId = createBigIntField("company_id")
    val employeeId = createVarcharField("employee_id")
    val name = createVarcharField("name")

    companion object{
        val instance = OwnersTable("owner")
    }
}
