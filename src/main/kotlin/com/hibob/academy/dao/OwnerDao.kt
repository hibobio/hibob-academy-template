package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper

class OwnerDao(private val sql: DSLContext) {

    private val ownerTable = OwnerTable.instance
    val petsTable = PetsTable()

    private val ownerMapper = RecordMapper<Record, OwnerData>
    { record ->
        OwnerData(
            record[ownerTable.id],
            record[ownerTable.name],
            record[ownerTable.companyId],
            record[ownerTable.employeeId]
        )
    }

    fun getAllOwners(companyId: Long): List<OwnerData> {
        return sql.select(ownerTable.id, ownerTable.name, ownerTable.companyId, ownerTable.employeeId)
            .from(ownerTable)
            .where(ownerTable.companyId.eq(companyId))
            .fetch(ownerMapper)
    }

    fun createOwnerIfNotExists(newOwnerData: OwnerDataInsert) {
        sql.insertInto(ownerTable)
            .set(ownerTable.name, newOwnerData.name)
            .set(ownerTable.companyId, newOwnerData.companyId)
            .set(ownerTable.employeeId, newOwnerData.employeeId)
            .onConflict(ownerTable.companyId, ownerTable.employeeId)
            .doNothing()
            .execute()

    }

    fun getOwnerByPetId(petId: Long, companyId: Long): OwnerData? {
        return sql.select(ownerTable.id, ownerTable.name, ownerTable.companyId, ownerTable.employeeId)
            .from(ownerTable)
            .join(petsTable).on(petsTable.ownerId.eq(ownerTable.id))
            .where(petsTable.id.eq(petId))
            .and(petsTable.companyId.eq(companyId))
            .fetchOne(ownerMapper)
    }
}