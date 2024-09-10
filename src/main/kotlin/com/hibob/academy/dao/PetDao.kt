package com.hibob.academy.dao

import jakarta.inject.Inject
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper

class PetDao @Inject constructor(private val sql: DSLContext) {
    private val pet = PetTable.instance

    private val petMapper = RecordMapper<Record, PetData>
    { record ->
        PetData (
            record[pet.name]
            record[pet.type]
            record[pet.companyId]
            record[pet.dateOfArrival]
        )
    }

    fun getPets(type: String): List<PetData> =
        sql.select(pet.name, pet.companyId, pet.dateOfArrival)
            .from(pet)
            .where(pet.type.eq(type))
            .fetch(petMapper)
}