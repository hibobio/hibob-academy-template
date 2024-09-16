package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable

class VaccineTable(tableName: String) : JooqTable(tableName) {
    val id = createBigIntField("id")
    val name = createVarcharField("name")

    companion object {
        val instance = VaccineTable("vaccine")
    }
}

class VaccineToPetTable(tableName: String) : JooqTable(tableName) {
    val vaccineId = createBigIntField("vaccine_id")
    val petId = createUUIDField("pet_id")
    val vaccinationDate = createDateField("vaccination_date")

    companion object {
        val instance = VaccineToPetTable("vaccine_to_pet")
    }
}