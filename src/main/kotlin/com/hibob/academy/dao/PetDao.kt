package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository
import java.util.*

@Repository
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

    fun createPet(pet: PetNoId): UUID {
        return sql.insertInto(petsTable)
            .set(petsTable.id, UUID.randomUUID())
            .set(petsTable.name, pet.name)
            .set(petsTable.type, pet.type)
            .set(petsTable.companyId, pet.companyId)
            .set(petsTable.dateOfArrival, pet.dateOfArrival)
            .set(petsTable.ownerId, pet.ownerId)
            .returning(petsTable.id)
            .fetchOne()!![petsTable.id]
    }

    fun assignOwnerIdToPet(petId: UUID, ownerId: UUID): Int {
        return sql.update(petsTable)
            .set(petsTable.ownerId, ownerId)
            .where(petsTable.id.eq(petId))
            .execute()
    }

    fun countPetsByType(companyId: Long): Map<String, Int> {
        val count = DSL.count(petsTable.type)
        return sql.select(petsTable.type, count)
            .from(petsTable)
            .where(petsTable.companyId.eq(companyId))
            .groupBy(petsTable.type)
            .fetch()
            .intoMap(
                { record -> record[petsTable.type].toString() },
                { record -> record[count].toInt() })
    }
}
