package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable
import com.hibob.academy.utils.asIs
import org.jooq.DSLContext
import org.springframework.stereotype.Component
import com.hibob.academy.utils.from

@Component
class OwnersDao(private val sql: DSLContext) {

    private val table = OwnersTable.instance

    fun getAllOwners(): List<Owner> {
        return sql.select(table.name, table.employeeId, table.companyId)
            .from(table)
            .fetch()
            .map { record ->
                mapToOwner(record)
            }
    }
}
