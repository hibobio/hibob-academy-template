package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.RecordMapper
import org.jooq.Record


class OwnerDao(private val sql: DSLContext) {
    data class Owner(val company_id: Long, val employee_id: String, val name: String)

    private val ownerTable = OwnersTable.instance

    val ownerMapper = RecordMapper<Record, Owner> { record ->
        Owner(record[ownerTable.companyId], record[ownerTable.employeeId], record[ownerTable.name])
    }

    fun getOwners(): List<Owner> =
        sql.select(ownerTable.companyId, ownerTable.employeeId, ownerTable.name)
            .from(ownerTable)
            .fetch(ownerMapper)

    fun createOwner(company_id: Long, employee_id: String, name: String) {
        sql.insertInto(ownerTable)
            .set(ownerTable.companyId, company_id)
            .set(ownerTable.employeeId, employee_id)
            .set(ownerTable.name, name)
            .onConflict(ownerTable.companyId, ownerTable.employeeId)
            .doNothing()
            .execute()
    }
}