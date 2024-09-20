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
    private val otherCompanyId = 2L

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

    @Test
    fun `get pets by ownerId when pets exist in the database`() {
        val ownerId = 1L
        val petTest3 = PetDataInsert(ownerId, name = "ci", type = PetType.CAT, companyId)

        petDao.createPet(petTest1)
        petDao.createPet(petTest3)

        val returnPet1 = petDao.getAllPetsByType(PetType.DOG, ownerId)[0]
        val returnPet3 = petDao.getAllPetsByType(PetType.CAT, ownerId)[0]

        val expectedResult = listOf(
            PetData(returnPet1.id, ownerId, petTest1.name, petTest1.type, petTest1.companyId, returnPet1.dateOfArrival),
            PetData(returnPet3.id, ownerId, petTest3.name, petTest3.type, petTest3.companyId, returnPet3.dateOfArrival)
        )

        val allPets = petDao.getPetsByOwnerId(ownerId, companyId)

        assertEquals(expectedResult, allPets)
    }

    @Test
    fun `get pets by ownerId when no pets exist for the owner`() {
        val ownerId = 1L

        val petsByOwner = petDao.getPetsByOwnerId(ownerId, companyId)

        assertTrue(petsByOwner.isEmpty())
    }

    @Test
    fun `get pets by ownerId when owner has pets in different company`() {
        val ownerId = 1L

        val petTest3 = PetDataInsert(ownerId, name = "ci", type = PetType.CAT, otherCompanyId)

        petDao.createPet(petTest1)
        petDao.createPet(petTest3)

        val returnPet1 = petDao.getAllPetsByType(PetType.DOG, ownerId)[0]

        val expectedResult = listOf(PetData(returnPet1.id, ownerId, petTest1.name, petTest1.type, petTest1.companyId, returnPet1.dateOfArrival))

        val actualResult = petDao.getPetsByOwnerId(ownerId, companyId)

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `count pets by type when pets exist in the database`() {
        val petTest3 = PetDataInsert(ownerId = 1L, name = "bi", type = PetType.CAT, companyId)

        petDao.createPet(petTest1)
        petDao.createPet(petTest2)
        petDao.createPet(petTest3)

        val countPetByType = petDao.countPetsByType(companyId)

        assertEquals(2, countPetByType[PetType.DOG])
        assertEquals(1, countPetByType[PetType.CAT])
    }

    @Test
    fun `count pets by type when no pets exist in the database`() {
        val countPetByType = petDao.countPetsByType(companyId)

        assertTrue(countPetByType.isEmpty())
    }
}