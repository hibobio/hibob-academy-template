package com.hibob.academy.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.random.Random

@BobDbTest
class OwnersDaoTest @Autowired constructor(private val sql: DSLContext)  {

    private val dao = OwnersDao(sql)
    val companyId = Random.nextLong()
    val table = OwnersTable.instance

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(table).where(table.companyId.eq(companyId)).execute()
    }

    @Test
    fun `inserting owner test`() {
        val owner = OwnerData("Gilad", companyId, "1")
        dao.insertOwner(owner)
        val ownersList = dao.getAllOwners()
        assertEquals(1, ownersList.size)
        assertEquals(OwnerData("Gilad", companyId, "1"), ownersList.get(0))
    }
}