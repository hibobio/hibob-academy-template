package com.hibob.academy.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@BobDbTest
class OwnerDaoTest @Autowired constructor(private val sql: DSLContext)  {

    private val ownerDao = OwnerDao(sql)
    val tableOwner = Owner.instance
    val companyId = 1L

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(tableOwner).where(tableOwner.companyId.eq(companyId)).execute()
    }

    @Test
    fun `get all owners wen we got owners in the database`() {
        val newOwner1 = OwnerData(name = "chezi", companyId, employeeId = "1")
        val newOwner2 = OwnerData(name = "chezi", companyId, employeeId = "2")

        ownerDao.createOwnerIfNotExists(newOwner1)
        ownerDao.createOwnerIfNotExists(newOwner2)

        assertEquals(2, ownerDao.getAllOwners().size)
    }

    @Test
    fun `get all owners wen we dont have owners in the database`() {
        assertEquals(0, ownerDao.getAllOwners().size)
    }



    @Test
    fun `create a new owner that doesn't exist in the database`() {
        val newOwner = OwnerData(name = "chezi", companyId, employeeId = "1")

        ownerDao.createOwnerIfNotExists(newOwner)
        assertTrue(newOwner in  ownerDao.getAllOwners())
    }

    @Test
    fun `create a new owner that exist in the database`() {
        val newOwner = OwnerData(name = "chezi", companyId = companyId, employeeId = "1")

        ownerDao.createOwnerIfNotExists(newOwner)
        ownerDao.createOwnerIfNotExists(newOwner)

        assertEquals(1, ownerDao.getAllOwners().size)
    }

    @Test
    fun `get owner by id wen we have owner in the database`() {
        val newOwner = OwnerData(name = "chezi", companyId = companyId, employeeId = "1")

        val newOwnerId = ownerDao.createOwnerIfNotExists(newOwner)

        assertEquals(newOwner, ownerDao.getOwnerById(newOwnerId))
    }

    @Test
    fun `traying to get owner by id wen we dont have this owner in the database`() {
        val newOwner = OwnerData(name = "chezi", companyId = companyId, employeeId = "1")
        ownerDao.createOwnerIfNotExists(newOwner)

        val newOwnerId = -1L

        assertEquals(null, ownerDao.getOwnerById(newOwnerId))
    }
}