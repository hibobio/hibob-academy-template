package com.hibob.academy.dao

import org.junit.jupiter.api.Assertions.*


import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate
import java.util.Date


@BobDbTest
class PetsDaoTest @Autowired constructor(private val sql: DSLContext)  {

    private val petDao = PetsDao(sql)
    val tablePets = Pets.instance
    val companyId = 1L

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(tablePets).where(tablePets.companyId.eq(companyId)).execute()
    }

    /*@Test
    fun `create a new pet that doesn't exist in the database`() {
        val petTest = PetData(ownerId = 1L, petId = null, name = "A", type = petDao.getType(PetsDao.PetType.DOG) , companyId =  1L, dateOfArrival = null)

        petDao.createPetIfNotExists(petTest)

        val filteredPets = petDao.getAllPetsByType(PetsDao.PetType.DOG)

        assertEquals(petTest.name, filteredPets[0].name)
        assertEquals(petTest.type, filteredPets[0].type)
        assertEquals(petTest.companyId, filteredPets[0].companyId)
    }

   @Test
    fun `create a new pet that exist in the database`() {
        val petTest = PetData(ownerId = 1L, petId = 1L, name = "A", type = petDao.getType(PetsDao.PetType.DOG) , companyId =  1L, dateOfArrival = null)

        petDao.createPetIfNotExists(petTest)
        petDao.createPetIfNotExists(petTest)

        val filteredPets = petDao.getAllPetsByType(PetsDao.PetType.DOG)

        assertEquals(1, filteredPets.size)
    }
*/
    @Test
    fun `return the pet using its id when it exists in the database`() {
        val petId = 1L
        val data = LocalDate.now()
        val petTest = PetData(ownerId = 1L, petId, name = "A", type = petDao.getType(PetsDao.PetType.DOG) , companyId =  1L, data)

        val newPetId = petDao.createPetIfNotExists(petTest)

        assertEquals(petTest, petDao.getPet(newPetId))
    }
}