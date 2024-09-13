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
            record[petsTable.ownersId]
        )
    }

    fun petsByType(type: String): List<Pet> =
        sql.select(petsTable.name, petsTable.companyId, petsTable.dateOfArrival, petsTable.ownersId)
            .from(petsTable)
            .where(petsTable.type.eq(type))
            .fetch(petsMapper)

    fun getPetsByOwnerId(ownerId: UUID): List<Pet> {
        return sql.select()
            .from(petsTable)
            .where(petsTable.ownersId.eq(ownerId))
            .fetch(petsMapper)
    }

    fun createPet(id: UUID?, name: String, type: String, companyId: Long, dateOfArrival: Date, ownerId: UUID?): UUID? {
        return sql.insertInto(petsTable)
            .set(petsTable.id, id ?: UUID.randomUUID())
            .set(petsTable.name, name)
            .set(petsTable.type, type)
            .set(petsTable.companyId, companyId)
            .set(petsTable.dateOfArrival, dateOfArrival)
            .set(petsTable.ownersId, ownerId)
            .returning(petsTable.id)
            .fetchOne()?.let { it[petsTable.id] }
    }

    fun assignOwnerIdToPet(petId: UUID, ownerId: UUID) {
        sql.update(petsTable)
            .set(petsTable.ownersId, ownerId)
            .where(petsTable.id.eq(petId))
            .execute()
    }

    fun getOwnerIdFromPetId(petId: UUID): UUID? {
        return sql.select(petsTable.ownersId)
            .from(petsTable)
            .where(petsTable.id.eq(petId))
            .fetchOne()?.let { it[petsTable.ownersId] }
    }
}