package com.hibob.academy.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import kotlin.random.Random

@BobDbTest
class PetDaoTest @Autowired constructor(private val sql: DSLContext){
    private val petDao = PetDao(sql)
    val companyId = Random.nextLong()
    val table = PetsTable.instance

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(table).where(table.companyId.eq(companyId)).execute()
    }



}