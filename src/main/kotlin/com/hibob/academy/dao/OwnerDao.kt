package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper

class OwnerDao(private val sql: DSLContext) {

    private val owner = Owner.instance

    private val ownerMapper = RecordMapper<Record, OwnerData>
    { record ->
        OwnerData(
            record[owner.name],
            record[owner.companyId],
            record[owner.employeeId]
        )
    }

    fun getAllOwners(): List<OwnerData> {
        return sql.select(owner.name, owner.companyId, owner.employeeId)
            .from(owner)
            .fetch(ownerMapper)
    }

    fun createOwnerIfNotExists(newOwnerData: OwnerData) {
        sql.insertInto(owner)
            .set(owner.name, newOwnerData.name)
            .set(owner.companyId, newOwnerData.companyId)
            .set(owner.employeeId, newOwnerData.employeeId)
            .onConflict(owner.companyId, owner.employeeId)
            .doNothing()
            .execute()
    }

    fun getOwnerById(id: Long): OwnerData? {
        return sql.select(owner.name, owner.companyId, owner.employeeId)
            .from(owner)
            .where(owner.ownerId.equal(id))
            .fetchOneInto(OwnerData::class.java)
    }




}