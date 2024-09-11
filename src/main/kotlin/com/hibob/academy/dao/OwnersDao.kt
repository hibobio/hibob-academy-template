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
class OwnersDao(private val sql: DSLContext) {

    private val table = OwnersTable.instance

    private val ownerMapper = RecordMapper<Record, OwnerData> {
        record ->
        OwnerData(record[table.name],
            record[table.companyId],
            record[table.employeeId]
        )
    }

    fun getAllOwners(): List<OwnerData> {
        return sql.select(table.name, table.employeeId, table.companyId)
            .from(table)
            .fetch(ownerMapper)
    }

    fun insertOwner(owner: OwnerData) {
        sql.insertInto(table)
        .set(table.name, owner.name)
        .set(table.companyId, owner.companyId)
        .set(table.employeeId, owner.employeeId)
        .onConflict(table.companyId, table.employeeId)
        .doNothing()
        .execute()
    }
}
