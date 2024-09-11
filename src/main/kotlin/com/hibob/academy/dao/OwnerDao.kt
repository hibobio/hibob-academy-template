package com.hibob.academy.dao

import jakarta.inject.Inject
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper

class OwnerDao @Inject constructor(private val sql: DSLContext) {

    private val owner = OwnerTable.instance

    private val ownerMapper = RecordMapper<Record, OwnerData>
    { record ->
        OwnerData (
            record[owner.name],
            record[owner.employeeId]
        )
    }

    fun getOwner(): List<OwnerData> =
        sql.select(owner.name, owner.employeeId, owner.companyId)
            .from(owner)
            .fetch(ownerMapper)
}