package com.hibob.academy.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.sql.Date
import java.time.LocalDate
import java.time.LocalDateTime
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

    @Test
    fun `get pets by type`(){
        petDao.createPet(
            name = "Jerry",
            type = "Dog",
            companyId = companyId,
            dateOfArrival = Date.valueOf(LocalDate.now()),
            ownerId = null
        )
        assertEquals("Jerry", petDao.petsByType("Dog").get(0).name ?:"fail")
    }

    @Test
    fun `get pets by type when pets with diffrent type`(){

    }

}