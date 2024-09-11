package com.hibob.academy.dao

import org.hibernate.validator.constraints.EAN.Type
import org.jooq.DSLContext
import org.jooq.RecordMapper
import org.jooq.Record
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.impl.DefaultConfiguration
import java.sql.Date // If you still need it
import java.time.LocalDate
class PetDao(private val sql: DSLContext) {

    private val petTable = PetTable.instance

    private val patMapper = RecordMapper<Record, PetData>  { record ->
        PetData(
            record[petTable.name],          // Use getValue for accessing field values
            record[petTable.dateOfArrival],    // Use getValue for accessing field values
            record[petTable.companyId],     // Use getValue for accessing field values
            record[petTable.ownerId]

        )
    }

    fun getPets(type: PetType): List<PetData> {
        return sql.select(petTable.name, petTable.dateOfArrival, petTable.companyId, petTable.type, petTable.ownerId)
            .from(petTable)
            .where(petTable.type.eq(type.name.lowercase()))
            .fetch(patMapper)
    }


    fun createNewPet(pet:PetDataType){
        sql.insertInto(petTable)
            .set(petTable.name ,pet.name)
            .set(petTable.ownerId, pet.ownerId)
            .set(petTable.dateOfArrival ,pet.dateOfArrival)
            .set(petTable.companyId ,pet.companyId)
            .set(petTable.ownerId ,pet.ownerId)
            .set(petTable.type ,pet.type.toDatabaseValue())
            .onConflict(petTable.companyId)
            .doNothing()
            .execute()
    }
}
