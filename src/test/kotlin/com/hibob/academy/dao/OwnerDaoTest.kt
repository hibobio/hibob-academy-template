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
        dao.createOwner(companyId, "aa", "bob")
        val owners = dao.getOwners()
        assertEquals(listOf(Owner(companyId, "aa", "bob")), owners)
    }

    @Test
    fun `not adding employee with same company and employee id`() {
        dao.createOwner(companyId, "aa", "bob")
        dao.createOwner(companyId, "aa", "John")
        assertEquals(listOf(Owner(companyId, "aa", "bob")), dao.getOwners())

    }

    @Test
    fun `create and read multiple owners`() {
        dao.createOwner(companyId, "aa", "bob")
        dao.createOwner(companyId, "bbb", "jerry")
        dao.createOwner(companyId, "ccc", "johans")
        assertEquals(3, dao.getOwners().size)
    }


    @Test
    fun `get owner by pet id`() {
        val ownerId = dao.createOwner(companyId, "aa", "bob")
        val petId = petDao.createPet("Jerry", "Dog", companyId, Date.valueOf(LocalDate.now()), ownerId)
        if (petId != null) {
            assertEquals(Owner(companyId, "aa", "bob"), dao.getOwnerByPetId(petId))
        }
    }


}