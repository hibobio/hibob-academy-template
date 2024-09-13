package com.hibob.academy.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@BobDbTest
class OwnerDaoTest @Autowired constructor(private val sql: DSLContext) {

    private val ownerDao = OwnerDao(sql)
    val tableOwner = Owner.instance
    val companyId = 1L
    val ownerId = 1L
    val name = "chezi"

    @AfterEach
    fun cleanup() {
        sql.deleteFrom(tableOwner).where(tableOwner.companyId.eq(companyId)).execute()
    }

    @Test
    fun `get all owners wen we got owners in the database`() {
        val newOwner1 = OwnerDataInsert( name, companyId, employeeId = "1")
        val newOwner2 = OwnerDataInsert( name, companyId, employeeId = "2")

        ownerDao.createOwnerIfNotExists(newOwner1)
        ownerDao.createOwnerIfNotExists(newOwner2)

        assertEquals(2, ownerDao.getAllOwners(companyId).size)
    }

    @Test
    fun `get all owners wen we dont have owners in the database`() {
        assertEquals(0, ownerDao.getAllOwners(ownerId).size)
    }


    @Test
    fun `create a new owner that doesn't exist in the database`() {
        val newOwner = OwnerDataInsert(name, companyId, employeeId = "1")
        ownerDao.createOwnerIfNotExists(newOwner)

        assertTrue(newOwner.name.equals(ownerDao.getAllOwners(companyId)[0].name))
        assertTrue(newOwner.companyId.equals(ownerDao.getAllOwners(companyId)[0].companyId))
        assertTrue(newOwner.employeeId.equals(ownerDao.getAllOwners(companyId)[0].employeeId))
    }

    @Test
    fun `create a new owner that exist in the database`() {
        val newOwner = OwnerDataInsert(name, companyId, employeeId = "1")

        ownerDao.createOwnerIfNotExists(newOwner)
        ownerDao.createOwnerIfNotExists(newOwner)

        assertEquals(1, ownerDao.getAllOwners(companyId).size)
    }

    @Test
    fun `get owner by id wen we have owner in the database`() {
        val newOwner = OwnerDataInsert(name, companyId, employeeId = "1")

        ownerDao.createOwnerIfNotExists(newOwner)

        val newOwnerId = ownerDao.getAllOwners(companyId)[0].ownerId

        assertEquals(newOwner.name, ownerDao.getOwnerById(newOwnerId, companyId)?.name)
        assertEquals(newOwner.companyId, ownerDao.getOwnerById(newOwnerId, companyId)?.companyId)
        assertEquals(newOwner.employeeId, ownerDao.getOwnerById(newOwnerId, companyId)?.employeeId)

    }

    @Test
    fun `traying to get owner by id wen we dont have this owner in the database`() {
        val newOwner = OwnerDataInsert(name, companyId, employeeId = "1")
        ownerDao.createOwnerIfNotExists(newOwner)

        val newOwnerId = -1L

        assertEquals(null, ownerDao.getOwnerById(newOwnerId, companyId))
    }
}