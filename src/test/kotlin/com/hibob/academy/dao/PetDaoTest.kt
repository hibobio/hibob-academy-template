package com.hibob.academy.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

@BobDbTest
class PetDaoTest @Autowired constructor(private val sql: DSLContext)  {
    private val companyId:Long=8
    private val ownerId:Long=25
    private val table=PetTable.instance
    private val dao = PetDao(sql)

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(table).where(table.companyId.eq(companyId)).execute()
    }

    @Test
    fun `make a new pet`() {
        val name = "Buddy"
        val dateOfArrival = LocalDate.of(2023, 5, 20)
        val ownerId = 456L
        val petTest = PetDataType(name, PetType.Dog ,dateOfArrival,companyId,ownerId)
        dao.createNewPet(petTest)
        // Use filter to find matching pets and check if the list is not empty
        val filteredPets = dao.getPets(PetType.Dog).filter { pet ->
            pet.name == petTest.name &&
                    pet.companyId == petTest.companyId &&
                    pet.dateOfArrival == petTest.dateOfArrival
        }

        assertTrue(filteredPets.isNotEmpty(), "Pet Waffle should have been added to the database")
    }

    @Test
    fun `get all pets in the same owner`() {
        val pet1 = PetDataType(
            "Rex",
            PetType.Dog,
            LocalDate.of(2024, 9, 11),
            companyId,
            ownerId
        )

        val pet2 = PetDataType(
            "Whiskers",
            PetType.Cat,
            LocalDate.of(2024, 9, 12),
            companyId,
            ownerId
        )

        val pet3 = PetDataType(
            "Buddy",
            PetType.Dog,
            LocalDate.of(2024, 9, 13),
            companyId,
            7)
        dao.createNewPet(pet1)
        dao.createNewPet(pet2)
        dao.createNewPet(pet3)
        val allPetById=dao.getPetsByOwnerId(ownerId)
        assertEquals(2, allPetById.size)

    }



}