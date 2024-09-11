package com.hibob.academy.dao

import jakarta.inject.Inject
import org.hibernate.validator.constraints.UUID
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper
import java.util.*

class PetDao(private val sql: DSLContext) {

    data class Pet(val name: String, val type: String, val companyId: Long, val dateOfArrival: Date)
    data class PetWithoutType(val name: String, val companyId: Long, val dateOfArrival: Date)

    private val petsTable = PetsTable.instance

    val petsMapper = RecordMapper<Record, PetWithoutType> { record ->
        PetWithoutType(
            record[petsTable.name],
            record[petsTable.companyId], record[petsTable.dateOfArrivel]
        )
    }

    fun petsByType(type: String): List<PetWithoutType> =
        sql.select(petsTable.name, petsTable.companyId, petsTable.dateOfArrivel)
            .from(petsTable)
            .where(petsTable.type.eq(type))
            .fetch(petsMapper)
}