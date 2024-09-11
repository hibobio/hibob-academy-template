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
        assertEquals(
            "Jerry",
            petDao.petsByType(type = "Dog")[0].name
        )
    }

    @Test
    fun `dont get pet with different type`(){
        petDao.createPet(name = "Jerry",
            type = "Dog",
            companyId = companyId,
            dateOfArrival = Date.valueOf(LocalDate.now()),
            ownerId = null)
        assertEquals(0, petDao.petsByType(type = "Cat").size)
    }


    @Test
    fun `get pets by type with multiple pets with different type`(){
        petDao.createPet(name = "Jerry",
            type = "Dog",
            companyId = companyId,
            dateOfArrival = Date.valueOf(LocalDate.now()),
            ownerId = null)
        petDao.createPet(name = "Johans",
            type = "Dog",
            companyId = companyId,
            dateOfArrival = Date.valueOf(LocalDate.now()),
            ownerId = null)
        petDao.createPet(name = "mitzi",
            type = "Cat",
            companyId = companyId,
            dateOfArrival = Date.valueOf(LocalDate.now()),
            ownerId = null)

        assertEquals(2, petDao.petsByType(type = "Dog").size)
    }



}