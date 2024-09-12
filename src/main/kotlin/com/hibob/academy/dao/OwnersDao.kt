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

    fun insertOwner(owner: OwnerData) {
        sql.insertInto(table)
        .set(table.id, owner.id)
        .set(table.name, owner.name)
        .set(table.companyId, owner.companyId)
        .set(table.employeeId, owner.employeeId)
        .onConflict(table.companyId, table.employeeId)
        .doNothing()
        .execute()
    }
}
