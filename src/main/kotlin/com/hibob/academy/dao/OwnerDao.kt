package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.RecordMapper
import org.jooq.Record
import java.util.*


class OwnerDao(private val sql: DSLContext) {

    private val ownerTable = OwnersTable.instance
    private val petTable = PetsTable.instance

    private val ownerMapper = RecordMapper<Record, Owner> { record ->
        Owner(
            record[ownerTable.id],
            record[ownerTable.companyId],
            record[ownerTable.employeeId],
            record[ownerTable.name]
        )
    }

    fun getOwners(companyId: Long): List<Owner> =
        sql.select(ownerTable.companyId, ownerTable.employeeId, ownerTable.name)
            .from(ownerTable)
            .where(ownerTable.companyId.eq(companyId))
            .fetch(ownerMapper)

    fun createOwner(ownerId: UUID?, companyId: Long, employeeId: String, name: String): UUID? {
        return sql.insertInto(ownerTable)
            .set(ownerTable.id, ownerId ?: UUID.randomUUID())
            .set(ownerTable.companyId, companyId)
            .set(ownerTable.employeeId, employeeId)
            .set(ownerTable.name, name)
            .onConflict(ownerTable.companyId, ownerTable.employeeId)
            .doNothing()
            .returning(ownerTable.id)
            .fetchOne()?.let { it[ownerTable.id] }
    }

    fun getOwnerByPetId(petId: UUID, companyId: Long): Owner? {
        return PetDao(sql).getOwnerIdFromPetId(petId)?.let { ownerId ->
            sql.select()
                .from(ownerTable)
                .where(ownerTable.id.eq(ownerId))
                .and(ownerTable.companyId.eq(companyId))
                .fetchOne(ownerMapper)
        }
    }

    fun getOwnerByPetIdUsingJoin(petId: UUID, companyId: Long): MutableList<Owner> {
        return sql.select(ownerTable.id, ownerTable.companyId, ownerTable.name, ownerTable.employeeId)
            .from(ownerTable)
            .leftJoin(petTable)
            .on(ownerTable.id.eq(petTable.ownersId))
            .where(petTable.id.eq(petId))
            .and(ownerTable.companyId.eq(companyId))
            .fetch(ownerMapper)
    }
}