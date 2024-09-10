package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper

class OwnerDao(private val sql: DSLContext) {

    private val o = Owner.instance

    private val ownerMapper = RecordMapper<Record, OwnerData>
    { record ->
        OwnerData(
            record[o.name],
            record[o.companyId],
            record[o.employeeId]
        )
    }

    fun getAllOwners() : List<OwnerData> =
        sql.select()
        .from(o)
        .fetch(ownerMapper)
}