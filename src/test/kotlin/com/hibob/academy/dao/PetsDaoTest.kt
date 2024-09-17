package com.hibob.academy.dao

import org.junit.jupiter.api.Assertions.*

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@BobDbTest
class PetsDaoTest @Autowired constructor(private val sql: DSLContext)  {

    private val petDao = PetsDao(sql)
    val tablePets = PetsTable.instance
    private val companyId = 1L

    val petTest1 = PetDataInsert(ownerId = 1L, name = "bi", type = PetType.DOG, companyId)
    val petTest2 = PetDataInsert(null, name = "bi", type = PetType.DOG, companyId)

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(tablePets).where(tablePets.companyId.eq(companyId)).execute()
    }

    @Test
    fun `get all pets by type wen we have pets in the data base`() {
        petDao.createPet(petTest1)
        val pet1 =  petDao.getAllPetsByType(PetType.DOG, companyId)[0]
        val date1 = pet1.dateOfArrival
        val id1 = pet1.id

        petDao.createPet(petTest2)
        val pet2 =  petDao.getAllPetsByType(PetType.DOG, companyId)[1]
        val date2 = pet2.dateOfArrival
        val id2 = pet2.id

        val expectedResult = listOf(
            PetData(id1, petTest1.ownerId, petTest1.name, petTest1.type, petTest1.companyId, date1),
            PetData(id2, petTest2.ownerId, petTest2.name, petTest2.type, petTest2.companyId, date2)
        )

        assertEquals(expectedResult, petDao.getAllPetsByType(PetType.DOG, companyId))
    }

    @Test
    fun `get all pets by type wen we dont have pets in the data base`() {
        val expectedResult = emptyList<PetData>()
        val allPetsInData = petDao.getAllPetsByType(PetType.DOG, companyId)

        assertEquals(expectedResult, allPetsInData)
    }

    @Test
    fun `update the pet ownerId wen the ownerId exist`() {
        petDao.createPet(petTest1)
        val pet =  petDao.getAllPetsByType(PetType.DOG, companyId)[0]
        val date = pet.dateOfArrival
        val id = pet.id

        val newOwnerId = 2L
        petDao.updatePetOwnerId(id, newOwnerId, companyId)

        val expectedResult = listOf(PetData(id, newOwnerId, petTest1.name, petTest1.type, petTest1.companyId, date))

        assertEquals(expectedResult, petDao.getAllPetsByType(PetType.DOG, companyId))
    }

    @Test
    fun `update the pet ownerId wen the ownerId is null`() {
        petDao.createPet(petTest2)
        val pet =petDao.getAllPetsByType(PetType.DOG, companyId)[0]
        val date = pet.dateOfArrival
        val id = pet.id

        val newOwnerId = 2L
        petDao.updatePetOwnerId(id, newOwnerId, companyId)

        val expectedResult = listOf(PetData(id, newOwnerId, petTest1.name, petTest1.type, petTest1.companyId, date))

        assertEquals(expectedResult, petDao.getAllPetsByType(PetType.DOG, companyId))
    }
}