package com.hibob.academy.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired

@BobDbTest
class OwnerDaoTest @Autowired constructor(private val sql: DSLContext) {

    private val ownerDao = OwnerDao(sql)
    val table = OwnerTable.instance
    private val companyId = 1L
    val owner = Owner(1,"Adi", null, null, companyId, "2")

    @Test
    fun `create owner test`() {
        ownerDao.createNewOwner(owner)
        assertEquals(1 ,ownerDao.getAllOwners().size)
    }

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(table)
            .where(table.companyId.eq(companyId))
            .execute()
    }
}