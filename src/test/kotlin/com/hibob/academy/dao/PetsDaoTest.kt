package com.hibob.academy.dao

import org.junit.jupiter.api.Assertions.*

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

@BobDbTest
class PetsDaoTest @Autowired constructor(private val sql: DSLContext)  {

    private val petDao = PetsDao(sql)
    val tablePets = Pets.instance
    val companyId = 1L
    val petId1 = 0L
    val data: LocalDate = LocalDate.now()
    val ownerId = 1L

    @AfterEach
    fun cleanup() {
        sql.deleteFrom(tablePets).where(tablePets.companyId.eq(companyId)).execute()
    }

    @Test
    fun `get all pets by type wen we have pets in the data base`() {
        val petId2 = 1L
        val petTest1 = PetDataInsert(petId1, name = "A", type = getType(PetType.DOG) , companyId)
        val petTest2 = PetDataInsert(petId2, name = "B", type = getType(PetType.DOG) , companyId)

        petDao.createPet(petTest1)
        petDao.createPet(petTest2)

        val allPetsInData = petDao.getAllPetsByType(PetType.DOG, companyId)

        assertEquals(2, allPetsInData.size)
    }

    @Test
    fun `get all pets by type wen we dont have pets in the data base`() {

        val allPetsInData = petDao.getAllPetsByType(PetType.DOG, companyId)

        assertEquals(0, allPetsInData.size)
    }

    @Test
    fun `create a new pet that doesn't exist in the database`() {
        val petTest = PetDataInsert(ownerId, name = "A", type = getType(PetType.DOG) , companyId)

        petDao.createPet(petTest)

        val filteredPets = petDao.getAllPetsByType(PetType.DOG, companyId)

        assertEquals(1, filteredPets.size)
    }


    @Test
    fun `return the pet using its id when it exists in the database`() {
        val petTest = PetDataInsert(ownerId, name = "A", type = getType(PetType.DOG) , companyId)

        val newPetId = petDao.createPet(petTest)

        assertEquals(petTest.ownerId, petDao.getPet(newPetId, companyId)?.ownerId)
        assertEquals(petTest.name, petDao.getPet(newPetId, companyId)?.name)
        assertEquals(petTest.type, petDao.getPet(newPetId, companyId)?.type)
        assertEquals(petTest.companyId, petDao.getPet(newPetId, companyId)?.companyId)
    }

    @Test
    fun `return null wen we trying to get pet that does not exist in the database`() {
        val petTest = PetDataInsert(ownerId, name = "A", type = getType(PetType.DOG) , companyId)
        petDao.createPet(petTest)

        val newPetId = -1L

        assertEquals(null, petDao.getPet(newPetId, companyId))
    }

    @Test
    fun `update the pet ownerId wen the ownerId exist`() {
        val petTest = PetDataInsert(ownerId, name = "A", type = getType(PetType.DOG), companyId)
        val newPetId = petDao.createPet(petTest)

        val newOwnerId = 2L

        petDao.updatePetOwnerId(newPetId, newOwnerId, companyId)

        assertEquals(ownerId, petDao.getPet(newPetId, companyId)?.ownerId)
    }

    @Test
    fun `update the pet owner with the owner id wen the ownerId is null`() {
        val petTest = PetDataInsert(null, name = "A", type = getType(PetType.DOG), companyId)
        val newPetId = petDao.createPet(petTest)

        val newOwnerId = 2L

        petDao.updatePetOwnerId(newPetId, newOwnerId, companyId)

        assertEquals(newOwnerId, petDao.getPet(newPetId, companyId)?.ownerId)
    }
}