package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable
import com.hibob.academy.utils.asIs
import org.jooq.DSLContext
import org.springframework.stereotype.Component
import com.hibob.academy.utils.from
import org.jooq.Record
import org.jooq.RecordMapper
import org.jooq.impl.DSL

@Component
class OwnerDao(private val sql: DSLContext) {

    private val table = OwnerTable.instance

    private val ownerMapper = RecordMapper<Record, OwnerData> {
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
