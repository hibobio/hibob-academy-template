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

    enum class PetType{
        DOG,
        CAT;
    }

    fun getAllPetsByType(type: PetType) : List<PetData> {
        sql.select(pet.ownerId, pet.petId, pet.name, pet.type, pet.companyId, pet.dateOfArrival)
            .from(pet)
            .where(pet.type.eq(type.toString()))
            .fetch(petMapper)
    }

    fun getType(type: PetType): String {
        return when (type) {
            PetType.DOG -> "DOG"
            PetType.CAT -> "CAT"
        }
    }

   /* fun covertStringTypeToEnum(pet: Pets): Enum<PetType> {
        return when (pet.type) {
            "DOG" -> PetType.DOG
            else -> PetType.CAT
    }*/
    fun createPetIfNotExists(newPetData: PetData) {
        sql.insertInto(pet)
            .set(pet.name, newPetData.name)
            .set(pet.type, newPetData.type)
            .set(pet.companyId, newPetData.companyId)
            .execute()
    }
}