package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.RecordMapper
import org.jooq.Record
import org.springframework.stereotype.Component

@Component
class PetsDao(private val sql: DSLContext) {

    private val petTable = PetsTable.instance

    private val petMapper = RecordMapper<Record, PetData>
    { record ->
        PetData(
            record[petTable.id],
            record[petTable.ownerId],
            record[petTable.name],
            enumValueOf<PetType>(record[petTable.type]),
            record[petTable.companyId],
            record[petTable.dateOfArrival]
        )
    }

    fun getAllPetsByType(type: PetType, companyId: Long) : List<PetData> {
        return sql.select(petTable.id, petTable.ownerId, petTable.name, petTable.type, petTable.companyId, petTable.dateOfArrival)
            .from(petTable)
            .where(petTable.type.eq(typeEnumToString(type)))
            .and(petTable.companyId.eq(companyId))
            .fetch(petMapper)
    }

    fun createPet(newPetData: PetDataInsert) {
        sql.insertInto(petTable)
            .set(petTable.ownerId, newPetData.ownerId)
            .set(petTable.name, newPetData.name)
            .set(petTable.type, typeEnumToString(newPetData.type))
            .set(petTable.companyId, newPetData.companyId)
            .execute()
    }

    fun updatePetOwnerId(petId: Long, ownerId: Long, companyId: Long) =
        sql.update(petTable)
            .set(petTable.ownerId, ownerId)
            .where(petTable.id.eq(petId))
            .and(petTable.companyId.eq(companyId))
            .execute()
}