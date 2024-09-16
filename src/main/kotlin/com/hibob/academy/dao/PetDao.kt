package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper
import java.sql.Date
import java.util.*

class PetDao(private val sql: DSLContext) {

    private val petsTable = PetsTable.instance

    private val petsMapper = RecordMapper<Record, Pet> { record ->
        Pet(
            record[petsTable.id],
            record[petsTable.name],
            record[petsTable.type],
            record[petsTable.companyId],
            record[petsTable.dateOfArrival],
            record[petsTable.ownerId]
        )
    }

    fun petsByType(type: String, companyId: Long): List<Pet> =
        sql.select(
            petsTable.id,
            petsTable.name,
            petsTable.type,
            petsTable.companyId,
            petsTable.dateOfArrival,
            petsTable.ownerId
        )
            .from(petsTable)
            .where(petsTable.type.eq(type))
            .and(petsTable.companyId.eq(companyId))
            .fetch(petsMapper)

    fun getPetsByOwnerId(ownerId: UUID, companyId: Long): List<Pet> {
        return sql.select()
            .from(petsTable)
            .where(petsTable.ownerId.eq(ownerId))
            .and(petsTable.companyId.eq(companyId))
            .fetch(petsMapper)
    }

    fun createPet(name: String, type: String, companyId: Long, dateOfArrival: Date, ownerId: UUID?): UUID? {
        return sql.insertInto(petsTable)
            .set(petsTable.id, UUID.randomUUID())
            .set(petsTable.name, name)
            .set(petsTable.type, type)
            .set(petsTable.companyId, companyId)
            .set(petsTable.dateOfArrival, dateOfArrival)
            .set(petsTable.ownerId, ownerId)
            .returning(petsTable.id)
            .fetchOne()?.let { it[petsTable.id] }
    }

    fun assignOwnerIdToPet(petId: UUID, ownerId: UUID) {
        sql.update(petsTable)
            .set(petsTable.ownerId, ownerId)
            .where(petsTable.id.eq(petId))
            .execute()
    }
}
