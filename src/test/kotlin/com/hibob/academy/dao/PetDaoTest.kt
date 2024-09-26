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
    private val companyId:Long = 8L
    private val table=PetTable.instance
    private val dao = PetDao(sql)

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(table).execute()
    }

    @Test
    fun `make a new pet`() {
        val name = "Buddy"
        dao.insertPet(name,PetType.DOG,companyId,null)
        val petsList = dao.getPetsByType(companyId,PetType.DOG)

        assertEquals(1, petsList.size)
        assertEquals(companyId, petsList[0].companyId)
        assertEquals("Buddy", petsList[0].name)
        assertEquals(PetType.DOG, petsList[0].type)
    }


    @Test
    fun `pet by type when in the db not exists pets with this type`() {
        val name = "Buddy"
        dao.insertPet(name,PetType.DOG,companyId,null)
        val petsList = dao.getPetsByType(companyId, PetType.CAT)
        assertEquals(emptyList<PetData>(), petsList)

    }


    @Test
    fun `adopt a pet`(){
        val name = "Buddy"
        dao.insertPet(name,PetType.DOG,companyId,null)
        val petsList = dao.getPetsByType(companyId, PetType.DOG)
        assertEquals(null, petsList[0].ownerId)

        dao.adoptPet(petsList[0].id, 1L,companyId)
        val petsListAfterUpdate = dao.getPetsByType(companyId, PetType.DOG)
        assertEquals(1L, petsListAfterUpdate[0].ownerId)
    }

    @Test
    fun `adopt a pet which is already adopted`() {
        val name = "Buddy"
        dao.insertPet(name,PetType.DOG,companyId,null)
        val pet = dao.getPetsByType(companyId, PetType.DOG)[0]
        dao.adoptPet(pet.id, 1L,companyId)
        dao.adoptPet(pet.id, 2L,companyId)
        val petsListAfterAdoption = dao.getPetsByType(companyId, PetType.DOG)
        assertEquals(2L, petsListAfterAdoption[0].ownerId)
    }


    @Test
    fun `getPetsByOwner returns all pets for owner Id `() {
        dao.insertPet("Buddy", PetType.DOG, companyId, -5)
        dao.insertPet("Bobi", PetType.DOG, companyId, -6)
        dao.insertPet("Bobi", PetType.CAT, companyId, -5)

        val actualPets = dao.getPetsByOwnerId(-5,companyId)


        assertEquals(2, actualPets.size)
        assertEquals("Buddy", actualPets[0].name)
        assertEquals("Bobi", actualPets[1].name)
        assertEquals(companyId, actualPets[0].companyId)
        assertEquals(companyId, actualPets[1].companyId)
    }

    @Test
    fun `countPetsByType should return correct count of pets by type`() {
        dao.insertPet("Buddy", PetType.DOG, companyId, null)
        dao.insertPet("Bobi", PetType.DOG, companyId, null)
        dao.insertPet("Bobi", PetType.CAT, companyId, null)

        val petCounts = dao.countPetsByType(companyId)
        assertEquals(2, petCounts.size)
        assertEquals(2, petCounts["dog"])
        assertEquals(1, petCounts["cat"])
    }


}