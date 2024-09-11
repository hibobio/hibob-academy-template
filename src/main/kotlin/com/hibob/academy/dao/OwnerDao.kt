package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.RecordMapper
import org.jooq.Record


class OwnerDao(private val sql: DSLContext) {

    private val ownerTable = OwnersTable.instance

    private val ownerMapper = RecordMapper<Record, Owner> { record ->
        Owner(record[ownerTable.companyId], record[ownerTable.employeeId], record[ownerTable.name])
    }

    fun getOwners(): List<Owner> =
        sql.select(ownerTable.companyId, ownerTable.employeeId, ownerTable.name)
            .from(ownerTable)
            .fetch(ownerMapper)

    fun createOwner(companyId: Long, employeeId: String, name: String) {
        sql.insertInto(ownerTable)
            .set(ownerTable.companyId, companyId)
            .set(ownerTable.employeeId, employeeId)
            .set(ownerTable.name, name)
            .onConflict(ownerTable.companyId, ownerTable.employeeId)
            .doNothing()
            .execute()
    }

}