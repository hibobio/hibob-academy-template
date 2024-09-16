package com.hibob.academy.dao

import com.hibob.academy.utils.BobDbTest
import jakarta.ws.rs.BadRequestException
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
class OwnerDaoTest @Autowired constructor(private val sql: DSLContext) {

    private val dao = OwnerDao(sql)
    private val ownerTable = OwnersTable.instance
    private val petTable = PetsTable.instance
    private val petDao = PetDao(sql)
    private val companyId = Random.nextLong()

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(ownerTable).where(ownerTable.companyId.eq(companyId)).execute()
        sql.deleteFrom(petTable).where(petTable.companyId.eq(companyId)).execute()
    }

    @Test
    fun `create and read`() {
        val ownerId = dao.createOwner(companyId, "aa", "bob")
        val owners = dao.getOwners(companyId)
        assertEquals(listOf(Owner(
            ownerId,
            companyId,
            "aa",
            "bob"
        )), owners)

    }

    @Test
    fun `not adding employee with same company and employee id`() {
        val ownerId = dao.createOwner(companyId, "aa", "bob")
        assertEquals(
            "Owner with the same company and employee id already exists",
            org.junit.jupiter.api.assertThrows<BadRequestException> { dao.createOwner(companyId, "aa", "John") }.message
        )
        assertEquals(listOf(Owner(
            ownerId,
            companyId,
            "aa",
            "bob"
        )), dao.getOwners(companyId))
    }

    @Test
    fun `create and read multiple owners`() {
        val ownerId1 = dao.createOwner(companyId, "aa", "bob")
        val ownerId2 = dao.createOwner(companyId, "bbb", "jerry")
        val ownerId3 = dao.createOwner(companyId, "ccc", "johans")
        assertEquals(
            listOf(
                Owner(
                    ownerId1,
                    companyId,
                    "aa",
                    "bob"
                ), Owner(
                    ownerId2,
                    companyId,
                    "bbb",
                    "jerry"
                ), Owner(
                    ownerId3,
                    companyId,
                    "ccc",
                    "johans"
                )
            ), dao.getOwners(companyId)
        )
    }


    @Test
    fun `get owner by pet id`() {
        val ownerId = dao.createOwner(companyId, "aa", "bob")
        val petId = petDao.createPet("Jerry", "Dog", companyId, Date.valueOf(LocalDate.now()), ownerId)
        assertEquals(listOf(Owner(
            ownerId,
            companyId,
            "aa",
            "bob"
        )), dao.getOwnerByPetId(petId, companyId))

    }

    @Test
    fun `get owner by pet id with multiple pets`() {
        val ownerId = dao.createOwner(companyId, "aa", "bob")
        val petId1 = petDao.createPet("Jerry", "Dog", companyId, Date.valueOf(LocalDate.now()), ownerId)
        val petId2 = petDao.createPet("Johns", "Cat", companyId, Date.valueOf(LocalDate.now()), ownerId)
        val petId3 = petDao.createPet("Mitzi", "Cow", companyId, Date.valueOf(LocalDate.now()), null)
        assertEquals(listOf(Owner(
            ownerId,
            companyId,
            "aa",
            "bob"
        )), dao.getOwnerByPetId(petId1, companyId))
        assertEquals(listOf(Owner(
            ownerId,
            companyId,
            "aa",
            "bob"
        )), dao.getOwnerByPetId(petId2, companyId))
        assertEquals(mutableListOf<Owner>(), dao.getOwnerByPetId(petId3, companyId))

    }
}