package com.hibob.academy.dao

import jakarta.inject.Inject
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper

class PetDao @Inject constructor(private val sql: DSLContext) {

    private val pet = PetTable.instance

    private val petMapper = RecordMapper<Record, Pet>
    { record ->
        Pet (
            id = record[pet.id],
            name = record[pet.name],
            type = record[pet.type],
            companyId = record[pet.companyId].toLong(),
            arrivalDate = record[pet.dateOfArrival]
        )
    }

    private val petWithoutTypeMapper = RecordMapper<Record, PetWithoutType>
    { record ->
        PetWithoutType (
            record[pet.id],
            record[pet.name],
            record[pet.companyId].toLong(),
            record[pet.dateOfArrival]
        )
    }

    fun getAllPetsFromType(type: PetType): List<PetWithoutType> =
        sql.select(pet.name, pet.companyId, pet.dateOfArrival)
            .from(pet)
            .where(pet.type.eq(getPetType(type)))
            .fetch(petWithoutTypeMapper)

    fun getAllPets(): List<Pet> =
        sql.select(pet.id, pet.name, pet.type, pet.companyId, pet.dateOfArrival)
            .from(pet)
            .fetch(petMapper)

    fun createNewPet(petData: Pet) {
        sql.insertInto(pet)
            .set(pet.name, petData.name)
            .set(pet.type, petData.type)
            .set(pet.companyId, petData.companyId)
            .set(pet.dateOfArrival, petData.arrivalDate)
            .onConflict(pet.companyId)
            .doNothing()
            .execute()
    }
}