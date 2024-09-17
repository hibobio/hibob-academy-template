package com.hibob.academy.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@BobDbTest
class OwnerDaoTest @Autowired constructor(private val sql: DSLContext) {

    private val ownerDao = OwnerDao(sql)
    private val petDao = PetsDao(sql)

    val tableOwner = OwnerTable.instance
    val tablePets = PetsTable.instance

    private val companyId = 1L
    val owner1 = OwnerDataInsert(name ="checi nikop", companyId, employeeId = "1" )
    val owner2 = OwnerDataInsert(name ="checi nikop", companyId, employeeId = "2" )

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(tableOwner).where(tableOwner.companyId.eq(companyId)).execute()
        sql.deleteFrom(tablePets).where(tablePets.companyId.eq(companyId)).execute()
    }

    @Test
    fun `get all owners wen we got owners in the database`() {
        ownerDao.createOwnerIfNotExists(owner1)
        val ownerId1 = ownerDao.getAllOwners(companyId)[0].id

        ownerDao.createOwnerIfNotExists(owner2)
        val ownerId2 = ownerDao.getAllOwners(companyId)[1].id

        val expectedResult = listOf(
            OwnerData(ownerId1, owner1.name, owner1.companyId, owner1.employeeId),
            OwnerData(ownerId2, owner2.name, owner2.companyId, owner2.employeeId),
        )

        assertEquals(expectedResult, ownerDao.getAllOwners(companyId))
    }

    @Test
    fun `get all owners wen we dont have owners in the database`() {
        val allOwners = ownerDao.getAllOwners(companyId)

        assertTrue(allOwners.isEmpty())
    }

    @Test
    fun `create a new owner`() {
        ownerDao.createOwnerIfNotExists(owner1)
        val ownerId = ownerDao.getAllOwners(companyId)[0].id
        val expectedResult = listOf(OwnerData(ownerId, owner1.name, owner1.companyId, owner1.employeeId))


        assertEquals(expectedResult, ownerDao.getAllOwners(companyId))
    }

    @Test
    fun `create a new owner null`() {
        val expectedResult = emptyList<OwnerData>()
        assertEquals(expectedResult, ownerDao.getAllOwners(companyId))
    }

    @Test
    fun `create the same owner`() {
        ownerDao.createOwnerIfNotExists(owner1)
        ownerDao.createOwnerIfNotExists(owner1)

        val ownerId = ownerDao.getAllOwners(companyId)[0].id

        val expectedResult = listOf(OwnerData(ownerId, owner1.name, owner1.companyId, owner1.employeeId))

        assertEquals(expectedResult, ownerDao.getAllOwners(companyId))
    }

    @Test
    fun `get owner info by petId`() {
        ownerDao.createOwnerIfNotExists(owner1)
        val newOwner = ownerDao.getAllOwners(companyId)[0]
        val ownerId = newOwner.id

        val petTest = PetDataInsert(ownerId , name = "A", PetType.DOG , companyId)
        petDao.createPet(petTest)
        val petId = petDao.getAllPetsByType(PetType.DOG, companyId)[0].id

        val checkOwnerTest = ownerDao.getOwnerByPetId(petId, companyId)

        assertEquals(newOwner, checkOwnerTest)
    }

    @Test
    fun `get null owner when no pet exists in the database`() {
        val ownerFromPetId = ownerDao.getOwnerByPetId(petId = 1L, companyId)

        assertNull(ownerFromPetId)
    }

    @Test
    fun `get null owner by petId when no owner exists in the database`() {
        val petDao = PetsDao(sql)
        val petTest = PetDataInsert(null , name = "A", PetType.DOG , companyId)
        petDao.createPet(petTest)
        val petId = petDao.getAllPetsByType(PetType.DOG, companyId)[0].id
        assertNull(ownerDao.getOwnerByPetId(petId, companyId))
    }
}