package com.hibob.academy.dao.ddlFlyWay

import com.hibob.academy.utils.JooqTable
import org.jooq.DSLContext

class VaccineToPetTable(tableName: String) : JooqTable(tableName) {
    val id = createBigIntField("id")
    val petId = createBigIntField("pet_id")
    val vaccinationDate = createDateField("vaccination_date")

    companion object {
        val instance = VaccineToPetTable("vaccineToPet")
    }
}

class VaccineToPetDao (private val sql: DSLContext) {
    private val table = VaccineToPetTable.instance
}