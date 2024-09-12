package com.hibob.academy.dao

import org.jooq.DSLContext
import org.springframework.stereotype.Component
import org.jooq.Record
import org.jooq.RecordMapper

@Component
class OwnerDao(private val sql: DSLContext) {

    private val table = OwnerTable.instance

    companion object val ownerMapper = RecordMapper<Record, OwnerData> {
        record ->
        OwnerData(record[table.id],
            record[table.name],
            record[table.companyId],
            record[table.employeeId]
        )
    }

    fun getAllOwners(companyId: Long): List<OwnerData> {
        return sql.select(table.id, table.name, table.companyId, table.employeeId)
            .from(table)
            .where(table.companyId.eq(companyId))
            .fetch(ownerMapper)
    }

    fun insertOwner(name: String, companyId: Long, employeeId: String) {
        sql.insertInto(table)
        .set(table.name, name)
        .set(table.companyId, companyId)
        .set(table.employeeId, employeeId)
        .onConflict(table.companyId, table.employeeId)
        .doNothing()
        .execute()
    }
}
