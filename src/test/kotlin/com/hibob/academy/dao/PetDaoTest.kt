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
class PetDaoTest @Autowired constructor(private val sql: DSLContext) {
    private val petDao = PetDao(sql)
    val companyId = Random.nextLong()
    val table = PetsTable.instance

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(table).where(table.companyId.eq(companyId)).execute()
    }

    @Test
    fun `get pets by type`() {
        val petId = petDao.createPet(
            PetNoId(
                name = "Jerry",
                type = "Dog",
                companyId = companyId,
                dateOfArrival = Date.valueOf(LocalDate.now()),
                ownerId = null
            )
        )
        assertEquals(
            listOf(
                Pet(
                    petId,
                    "Jerry",
                    "Dog",
                    companyId,
                    Date.valueOf(LocalDate.now()),
                    null
                )
            ),
            petDao.petsByType(type = "Dog", companyId)
        )
    }

    @Test
    fun `dont get pet with different type`() {
        petDao.createPet(
            PetNoId(
                name = "Jerry",
                type = "Dog",
                companyId = companyId,
                dateOfArrival = Date.valueOf(LocalDate.now()),
                ownerId = null
            )
        )
        assertEquals(emptyList<Pet>(), petDao.petsByType(type = "Cat", companyId))
    }


    @Test
    fun `get pets by type with multiple pets with different type`() {
        val petId1 = petDao.createPet(
            PetNoId(
                name = "Jerry",
                type = "Dog",
                companyId = companyId,
                dateOfArrival = Date.valueOf(LocalDate.now()),
                ownerId = null
            )
        )
        val petId2 = petDao.createPet(
            PetNoId(
                name = "Johans",
                type = "Dog",
                companyId = companyId,
                dateOfArrival = Date.valueOf(LocalDate.now()),
                ownerId = null
            )
        )
        petDao.createPet(
            PetNoId(
                name = "mitzi",
                type = "Cat",
                companyId = companyId,
                dateOfArrival = Date.valueOf(LocalDate.now()),
                ownerId = null
            )
        )

        assertEquals(
            listOf(
                Pet(
                    petId1,
                    name = "Jerry",
                    type = "Dog",
                    companyId = companyId,
                    dateOfArrival = Date.valueOf(LocalDate.now()),
                    ownerId = null
                ),
                Pet(
                    petId2,
                    name = "Johans",
                    type = "Dog",
                    companyId = companyId,
                    dateOfArrival = Date.valueOf(LocalDate.now()),
                    ownerId = null
                )
            ), petDao.petsByType(type = "Dog", companyId)
        )
    }

    @Test
    fun `get pets by ownerId with few pets`() {
        val ownerId = UUID.randomUUID()
        val petId1 = petDao.createPet(
            PetNoId(
                "Jerry",
                "Dog",
                companyId,
                Date.valueOf(LocalDate.now()),
                ownerId
            )
        )
        val petId2 = petDao.createPet(
            PetNoId(
                "Johans",
                "Cat",
                companyId,
                Date.valueOf(LocalDate.now()),
                ownerId
            )
        )
        val petId3 = petDao.createPet(
            PetNoId(
                "mitzi",
                "Cow",
                companyId,
                Date.valueOf(LocalDate.now()),
                ownerId
            )
        )
        assertEquals(
            listOf(
                Pet(
                    petId1,
                    "Jerry",
                    "Dog",
                    companyId,
                    Date.valueOf(LocalDate.now()),
                    ownerId
                ), Pet(
                    petId2,
                    "Johans",
                    "Cat",
                    companyId,
                    Date.valueOf(LocalDate.now()),
                    ownerId
                ), Pet(
                    petId3,
                    "mitzi",
                    "Cow",
                    companyId,
                    Date.valueOf(LocalDate.now()),
                    ownerId
                )
            ), petDao.getPetsByOwnerId(ownerId, companyId)
        )
    }

    @Test
    fun `get pets by ownerId with few owners`() {
        val ownerId = UUID.randomUUID()
        val petId = petDao.createPet(
            PetNoId(
                "Jerry",
                "Dog",
                companyId,
                Date.valueOf(LocalDate.now()),
                ownerId
            )
        )
        petDao.createPet(
            PetNoId(
                "Johans",
                "Cat",
                companyId,
                Date.valueOf(LocalDate.now()),
                UUID.randomUUID()
            )
        )
        petDao.createPet(
            PetNoId(
                "mitzi",
                "Cow",
                companyId,
                Date.valueOf(LocalDate.now()),
                UUID.randomUUID()
            )
        )
        assertEquals(
            listOf(
                Pet(
                    petId,
                    "Jerry",
                    "Dog",
                    companyId,
                    Date.valueOf(LocalDate.now()),
                    ownerId
                )
            ),
            petDao.getPetsByOwnerId(ownerId, companyId)
        )
    }

    @Test
    fun `updating ptes owner id`() {
        val ownerId = UUID.randomUUID()
        val petId = petDao.createPet(
            PetNoId(
                "Jerry",
                "Dog",
                companyId,
                Date.valueOf(LocalDate.now()),
                null
            )
        )
        petDao.assignOwnerIdToPet(petId, ownerId)
        assertEquals(
            listOf(
                Pet(
                    petId,
                    "Jerry",
                    "Dog",
                    companyId,
                    Date.valueOf(LocalDate.now()),
                    ownerId
                )
            ),
            petDao.getPetsByOwnerId(ownerId, companyId)
        )

    }

    @Test
    fun `count by type`() {
        petDao.createPet(PetNoId("Jerry", "Dog", companyId, Date.valueOf(LocalDate.now()), null))
        petDao.createPet(PetNoId("Johans", "Dog", companyId, Date.valueOf(LocalDate.now()), null))
        petDao.createPet(PetNoId("jj", "Cat", companyId, Date.valueOf(LocalDate.now()), null))
        petDao.createPet(PetNoId("hh", "Cat", companyId, Date.valueOf(LocalDate.now()), null))
        assertEquals(mapOf("Dog" to 2, "Cat" to 2), petDao.countPetsByType(companyId))
    }


}