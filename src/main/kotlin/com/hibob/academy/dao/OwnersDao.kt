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

    private val ownerMapper = RecordMapper<Record, Owner> {
        record ->
        Owner(record[table.id],
            record[table.name],
            record[table.companyId],
            record[table.employeeId]
        )
    }

    fun getAllOwners(): List<Owner> {
        return sql.select(table.name, table.employeeId, table.companyId)
            .from(table)
            .fetch(ownerMapper)
    }
}
