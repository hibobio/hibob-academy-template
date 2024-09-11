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

    @BeforeEach
    @Test
    fun `create a new owner that doesn't exist in the database`() {
        val newOwner = OwnerData(name = "chezi", companyId = companyId, employeeId = "1")

        ownerDao.createOwnerIfNotExists(newOwner)

        val allOwnersInData = ownerDao.getAllOwners()

        assertEquals(newOwner.name, allOwnersInData[0].name)
        assertEquals(newOwner.companyId, allOwnersInData[0].companyId)
        assertEquals(newOwner.employeeId, allOwnersInData[0].employeeId)
    }

    @Test
    fun `create a new owner that exist in the database`() {
        val newOwner = OwnerData(name = "chezi", companyId = companyId, employeeId = "1")

        ownerDao.createOwnerIfNotExists(newOwner)
        ownerDao.createOwnerIfNotExists(newOwner)

        val allOwnersInData = ownerDao.getAllOwners()

        assertEquals(1, allOwnersInData.size)
    }
}