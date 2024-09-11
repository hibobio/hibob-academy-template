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
    private val table = OwnersTable.instance
    val companyId = Random.nextLong()

    @Test
    fun `create and read`(){
        dao.createOwner(companyId, "aa", "bob")
        val owners = dao.getOwners()
        assertEquals(listOf(Owner(companyId, "aa", "bob")), owners)
    }

    @Test
    fun `create and read multiple owners`(){
        dao.createOwner(companyId, "aa", "bob")
        dao.createOwner(companyId, "bbb", "jerry")
        dao.createOwner(companyId, "ccc", "johans")
        assertEquals(3, dao.getOwners().size)
    }

    @Test
    fun `owner info by petid`(){
        val petsDao = PetDao(sql)
        val ownerId= dao.createOwner(companyId, "aa", "bob")
        val petId = petsDao.createPet("Jerry", "Dog", companyId, Date.valueOf(LocalDate.now()), ownerId)

        val ownerIdFromPetTable = petsDao.getPetsByOwnerId(ownerId)[0].ownersId
        assertEquals(ownerIdFromPetTable, ownerId)
    }

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(table).where(table.companyId.eq(companyId)).execute()
    }
}