package com.hibob.academy.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.random.Random


@BobDbTest
class OwnerDaoTest @Autowired constructor(private val sql: DSLContext)  {

    private val ownerDao = OwnerDao(sql)
    val tableOwner = Owner.instance
    val companyId = Random.nextLong()

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(tableOwner).where(tableOwner.companyId.eq(companyId)).execute()
    }

    @BeforeEach
    @Test
    fun `create a new owner that doesn't exist in the database`() {
        val newOwner = OwnerData("chezi", companyId, "1")

        ownerDao.createOwnerIfNotExists(newOwner)

        val allOwnersInData = ownerDao.getAllOwners()

        assertEquals(1, allOwnersInData.size)
    }

    //creat new owner that exist


    //clean app

}