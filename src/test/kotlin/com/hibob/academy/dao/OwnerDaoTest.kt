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
import java.util.*
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
        val ownerId = UUID.randomUUID()
        dao.createOwner(ownerId, companyId, "aa", "bob")
        val owners = dao.getOwners(companyId)
        assertEquals(listOf(Owner(ownerId, companyId, "aa", "bob")), owners)
    }

    @Test
    fun `not adding employee with same company and employee id`() {
        val ownerId = UUID.randomUUID()
        dao.createOwner(ownerId, companyId, "aa", "bob")
        dao.createOwner(null, companyId, "aa", "John")
        assertEquals(listOf(Owner(ownerId, companyId, "aa", "bob")), dao.getOwners(companyId))

    }

    @Test
    fun `create and read multiple owners`() {
        dao.createOwner(null, companyId, "aa", "bob")
        dao.createOwner(null, companyId, "bbb", "jerry")
        dao.createOwner(null, companyId, "ccc", "johans")
        assertEquals(3, dao.getOwners(companyId).size)
    }


    @Test
    fun `get owner by pet id`() {
        val ownerId = UUID.randomUUID()
        dao.createOwner(ownerId, companyId, "aa", "bob")
        val petId = petDao.createPet(null, "Jerry", "Dog", companyId, Date.valueOf(LocalDate.now()), ownerId)
        if (petId != null) {
            assertEquals(Owner(ownerId, companyId, "aa", "bob"), dao.getOwnerByPetIdUsingJoin(petId, companyId)[0])
        }
    }


}