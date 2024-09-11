package com.hibob.academy.dao

import jakarta.inject.Inject
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper

class OwnerDao @Inject constructor(private val sql: DSLContext) {

    private val owner = OwnerTable.instance

    private val ownerMapper = RecordMapper<Record, Owner>
    { record ->
        Owner (
            id = record[owner.id],
            name = record[owner.name],
            firstName = null,
            lastName = null,
            companyId = record[owner.companyId].toLong(),
            employeeId = record[owner.employeeId]
        )
    }

    fun getAllOwners(): List<Owner> =
        sql.select(owner.id, owner.name, owner.employeeId, owner.companyId)
            .from(owner)
            .fetch(ownerMapper)

    fun createNewOwner(ownerData: Owner) {
        sql.insertInto(owner)
            .set(owner.name, ownerData.name)
            .set(owner.companyId, ownerData.companyId)
            .set(owner.employeeId, ownerData.employeeId)
            .onConflict(owner.companyId, owner.employeeId)
            .doNothing()
            .execute()
    }
}