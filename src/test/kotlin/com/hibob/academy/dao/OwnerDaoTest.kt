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
class OwnerDaoTest @Autowired constructor(private val sql: DSLContext) {

    private val dao = OwnerDao(sql)
    private val table = OwnersTable.instance
    val companyId = Random.nextLong()

    @Test
    fun `create and read`(){
        dao.createOwner(companyId, "abc", "bob")
        val owners = dao.getOwners()
        //assertEquals(listOf(OwnerDao.Owner(companyId,"abc", "bob" )), owners)
    }




    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(table).where(table.companyId.eq(companyId)).execute()
    }
}