package com.hibob.academy.service

import com.hibob.academy.dao.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.time.LocalDate

class PetsServiceTest {
    private val petDao = mock<PetsDao>()
    private val petsService = PetsService(petDao)

    @Test
    fun `getAllPestByType should return list of pets`() {
        val companyId = 1L
        val pet1 = PetData(id = 1L, ownerId = 1L, name = "chezi", type = PetType.DOG, companyId, dateOfArrival = LocalDate.now())
        val pet2 = PetData(id = 2L, ownerId = 2L, name = "chezi", type = PetType.DOG, companyId, dateOfArrival = LocalDate.now())
        val listOfPets = listOf(pet1, pet2)

        whenever(petDao.getAllPetsByType(PetType.DOG, companyId)).thenReturn(listOfPets)

        assertEquals(listOfPets, petsService.getAllPetsByType(PetType.DOG, companyId))
    }

    @Test
    fun `createPet should call DAO to create pet`() {
        val companyId = 1L
        val pet1 = PetDataInsert(ownerId = 1L, name = "chezi", type = PetType.DOG, companyId)

        petsService.createPet(pet1)

        verify(petDao).createPet(any())
    }

    @Test
    fun `updatePetOwnerId should call DAO to update owner`() {
        val companyId = 1L
        val petId = 1L
        val pet = PetData(petId, ownerId = 1L, name = "dog", type = PetType.DOG, companyId, dateOfArrival = LocalDate.now())
        val owner1 = OwnerData(id = 1L, name = "chezi", companyId, employeeId = "1")

        petDao.updatePetOwnerId(petId, ownerId = 1L, companyId)

        verify(petDao).updatePetOwnerId(pet.id, owner1.id, companyId)
    }

    @Test
    fun `updatePetOwnerId should throw exception wen the pet is not found`() {
        val companyId = 1L
        val petId = 1L
        val newOwnerId = 2L

        whenever(petDao.updatePetOwnerId(petId, newOwnerId, companyId)).thenReturn(0)

        val exception = assertThrows<IllegalArgumentException> {
            petsService.updatePetOwnerId(petId, companyId, newOwnerId)
        }

        assertEquals("the information you entered does not match the database", exception.message )
    }
}