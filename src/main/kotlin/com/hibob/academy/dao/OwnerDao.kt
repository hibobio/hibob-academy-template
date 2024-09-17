package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper
import org.springframework.stereotype.Component

@Component
class OwnerDao (private val sql: DSLContext) {
    private val ownerTable = OwnerTable.instance
    private val petTable = PetTable.instance

    private val ownerMapper = RecordMapper<Record, OwnerData>  { record ->
        OwnerData(
            record[ownerTable.id],
            record[ownerTable.name],
            record[ownerTable.employeeId],
            record[ownerTable.companyId]
        )
    }

    fun getAllOwner(companyId:Long): List<OwnerData> {
        return sql.select(
            ownerTable.id,
            ownerTable.name,
            ownerTable.employeeId,
            ownerTable.companyId
        )
            .from(ownerTable)
            .where(ownerTable.companyId.eq(companyId))
            .fetch(ownerMapper)
    }

    fun createNewOwner(name:String,employeeId:Long,companyId: Long){
        sql.insertInto(ownerTable)
            .set(ownerTable.name ,name)
            .set(ownerTable.companyId ,companyId)
            .set(ownerTable.employeeId ,employeeId)
            .onConflict(ownerTable.companyId,ownerTable.employeeId)
            .doNothing()
            .execute()
    }


    fun getOwnerByPetId(petId: Long, companyId: Long): OwnerData? {
        return sql.select(
            ownerTable.id,
            ownerTable.name,
            ownerTable.employeeId,
            ownerTable.companyId
        )
            .from(ownerTable)
            .join(petTable).on(petTable.ownerId.eq(ownerTable.id)) // Assuming ownerId from pets table relates to id from owner table
            .where(petTable.id.eq(petId))
            .and(petTable.companyId.eq(companyId))
            .fetchOne(ownerMapper)//OwnerData::class.java)
    }

}