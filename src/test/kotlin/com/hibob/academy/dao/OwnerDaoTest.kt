package com.hibob.academy.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

@BobDbTest
class OwnerDaoTest @Autowired constructor(private val sql: DSLContext){

    private val companyId:Long=8
    private val ownerTable=OwnerTable.instance
    private val petTable=PetTable.instance
    private val dao = OwnerDao(sql)
    private val petDao = PetDao(sql)

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(ownerTable).where(ownerTable.companyId.eq(companyId)).execute()
        sql.deleteFrom(petTable).where(petTable.companyId.eq(companyId)).execute()
    }

    @Test
    fun `get all owners by company Id`() {
        dao.createNewOwner("Matan",222,companyId)
        dao.createNewOwner("Nati",223,companyId)
        dao.createNewOwner("Nisim",224,companyId)
        dao.createNewOwner("Tami",225,7)
        dao.createNewOwner("Tom",226,4)
        val owners = dao.getAllOwner(companyId)
        assertEquals(3, owners.size)
        assertEquals(owners[0].name, "Matan")
        assertEquals(owners[1].name, "Nati")
        assertEquals(owners[2].name, "Nisim")

    }

    @Test
    fun `make a new owner`() {
        dao.createNewOwner("matan",222,companyId)
        val ownerTest=dao.getAllOwner(companyId)
        assertTrue(dao.getAllOwner(companyId).contains(ownerTest[0]))
    }

    @Test
    fun `insert an owner with the same companyId and employeeId`() {
        dao.createNewOwner("Matan",222,companyId)
        dao.createNewOwner("Nati",222,companyId)
        val ownersList = dao.getAllOwner(companyId)
        assertEquals(1, ownersList.size)
        assertEquals("Matan", ownersList.get(0).name)
        assertEquals(222, ownersList.get(0).employeeId)
    }


    @Test
    fun `get owner info by pet id`() {
        dao.createNewOwner("Matan",222,companyId)
        val ownersList = dao.getAllOwner(companyId)
        petDao.insertPet("Buddy", PetType.DOG, companyId, ownersList[0].id)
        petDao.insertPet("Max", PetType.CAT, companyId, null)
        val petIdWithOwner = petDao.getPetsByType(companyId,PetType.DOG)

        val ownerDataWithOwner = dao.getOwnerByPetId(petIdWithOwner[0].id,companyId)
        assertNotNull(ownerDataWithOwner)
        assertEquals("Matan", ownerDataWithOwner?.name)
        assertEquals(222, ownerDataWithOwner?.employeeId)
        assertEquals(companyId, ownerDataWithOwner?.companyId)
    }
}