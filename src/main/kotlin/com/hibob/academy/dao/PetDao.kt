package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.RecordMapper
import org.jooq.Record
import org.jooq.impl.DSL
import org.springframework.stereotype.Component
import java.time.LocalDate


@Component
class PetDao(private val sql: DSLContext) {
    private val ownerTable = OwnerTable.instance
    private val petTable = PetTable.instance

    private val petMapper = RecordMapper<Record, PetData>  { record ->
        PetData(
            record[petTable.id],
            record[petTable.name],
            PetType.fromDatabaseValue(record[petTable.type]),
            record[petTable.dateOfArrival],
            record[petTable.companyId],
            record[petTable.ownerId]

        )
    }

    fun getPetsByType(companyId:Long,type: PetType): List<PetData> {
        return sql.select(petTable.id,petTable.name, petTable.dateOfArrival, petTable.companyId, petTable.type, petTable.ownerId)
            .from(petTable)
            .where(petTable.type.eq(type.name.lowercase()))
            .and(petTable.companyId.eq(companyId))
            .fetch(petMapper)
    }



    fun insertPet(name: String, type: PetType,companyId: Long ,ownerId:Long?) {
        sql.insertInto(petTable)
            .set(petTable.name ,name)
            .set(petTable.ownerId, ownerId)
            .set(petTable.dateOfArrival , LocalDate.now())
            .set(petTable.companyId ,companyId)
            .set(petTable.type ,type.toDatabaseValue())
            .execute()
    }


    fun adoptPet(petId: Long, ownerId: Long,companyId :Long){
        sql.update(petTable)
            .set(petTable.ownerId, ownerId)
            .where(petTable.id.eq(petId))
            .and(petTable.companyId.eq(companyId))
            .execute()
    }

    fun getPetsByOwnerId(ownerId: Long,companyId :Long): List<PetData> {
        return sql.select()
            .from(petTable)
            .where(petTable.companyId.eq(companyId))
            .and(petTable.ownerId.eq(ownerId))
            .fetch(petMapper)
    }


    fun countPetsByType(companyId: Long): Map<String, Int> {
        return sql.select(petTable.type, DSL.count())
            .from(petTable)
            .groupBy(petTable.type)
            .fetch()
            .intoMap(petTable.type, DSL.count())
    }

}
