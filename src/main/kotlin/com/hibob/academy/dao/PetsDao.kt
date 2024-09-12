package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.RecordMapper
import org.jooq.Record


class PetsDao(private val sql: DSLContext) {

    private val pet = Pets.instance

    private val petMapper = RecordMapper<Record, PetData>
    { record ->
        PetData(
            record[pet.ownerId],
            record[pet.petId],
            record[pet.name],
            record[pet.type],
            record[pet.companyId],
            record[pet.dateOfArrival]
        )
    }

    enum class PetType {
        DOG,
        CAT;
    }

    fun getAllPetsByType(type: PetType) : List<PetData> {
        return sql.select(pet.ownerId, pet.petId, pet.name, pet.type, pet.companyId, pet.dateOfArrival)
            .from(pet)
            .where(pet.type.eq(getType(type)))
            .fetch(petMapper)
    }

    fun getType(type: PetType): String {
        return when (type) {
            PetType.DOG -> "DOG"
            PetType.CAT -> "CAT"
        }
    }

    fun createPetIfNotExists(newPetData: PetData) {
        sql.insertInto(pet)
            .set(pet.ownerId, newPetData.ownerId)
            .set(pet.name, newPetData.name)
            .set(pet.type, newPetData.type)
            .set(pet.companyId, newPetData.companyId)
            .execute()
    }

    fun getOwnerIdFromPetId(petId: Long): PetData? {
        return sql.select(pet.ownerId)
            .from(pet)
            .where(pet.petId.eq(petId))
            .fetchOneInto(PetData::class.java)
    }

    fun  UpdateThePetWithTheOwnerID(petId: Long, ownerId: Long) {
        sql.update(pet)
            .set(pet.ownerId, ownerId)
            .where(pet.petId.eq(petId).and(pet.ownerId.isNull))
            .execute()
    }
}