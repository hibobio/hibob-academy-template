package com.hibob.academy.dao

import org.junit.jupiter.api.Assertions.*


import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random


@BobDbTest
class PetsDaoTest @Autowired constructor(private val sql: DSLContext)  {

    private val petDao = PetsDao(sql)
    val tablePets = Pets.instance
    val dateOfArrival = LocalDate.of(2021,  1,1)

    //creat new owner
    @Test
    fun `create a new pet that doesn't exist in the database`() {
        val petTest = PetData(1L, 1L, "dog", petDao.getType(PetsDao.PetType.DOG) ,  1L, dateOfArrival)

        petDao.createPetIfNotExists(petTest)

        val filteredPets = petDao.getAllPetsByType(PetsDao.PetType.DOG)

        assertEquals(petTest, filteredPets[0])
    }

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(tablePets).where(tablePets.companyId.eq(companyId)).execute()
    }

    @Test
    fun `inserting pet test`() {
        val pet = PetData("Murphy", "dog", companyId, 1)
        dao.insertPet(pet)
        val petsList = dao.petsByType(PetType.DOG)
        assertEquals(1, petsList.size)
        assertEquals(PetData("Murphy", "dog", companyId, 1), petsList.get(0))
    }
}

