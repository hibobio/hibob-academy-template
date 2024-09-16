package com.hibob.academy.service

import org.junit.jupiter.api.assertThrows
import com.hibob.academy.dao.PetDao
import com.hibob.academy.dao.PetData
import com.hibob.academy.dao.PetType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDate

class PetServiceTest{
        private val petDaoMock = mock<PetDao>{}
        private val petService = PetService(petDaoMock)
        private val validCompanyId = 8L
        private val illegalCompanyId = -1L
        private val validOwnerId= 5L
        private val validName = "Matan"
        private val ownerId = 10L
        private val illegalOwnerId = -10L
        private val validPetType = "DOG"
        private val petTypeDog = PetType.DOG
        private  val validPetId= 7L
        private  val illegalPetId= -7L


        @Test
        fun `getPetsByType should throw exception if companyId is less than zero`() {
            val exception = assertThrows<IllegalArgumentException> {
                petService.getPetsByType(illegalCompanyId, validPetType)
            }
            assertEquals("Invalid companyId", exception.message)
            verify(petDaoMock, never()).getPetsByType(validCompanyId, petTypeDog)
        }

        @Test
        fun `getPetsByType should throw exception if type is null`() {
            val exception = assertThrows<IllegalArgumentException> {
                petService.getPetsByType(validCompanyId, null)
            }
            assertEquals("Invalid type", exception.message)
            verify(petDaoMock, never()).getPetsByType(validCompanyId, petTypeDog)
        }

        @Test
        fun `getPetsByType should return pets successfully`() {
            val expectedPets = listOf(
                PetData(1, "Buddy",  petTypeDog, LocalDate.now(),validCompanyId,ownerId),
                PetData( 2, "Max",  petTypeDog,LocalDate.now(),validCompanyId,ownerId)
            )

            whenever(petDaoMock.getPetsByType(validCompanyId, petTypeDog)).thenReturn(expectedPets)

            val actualPets = petService.getPetsByType(validCompanyId, validPetType)

            assertEquals(expectedPets, actualPets)
            verify(petDaoMock).getPetsByType(validCompanyId, petTypeDog)
        }


        @Test
        fun `insertPet should throw exception if name is empty`() {
            val exception = assertThrows<IllegalArgumentException> {
                petService.insertPet("", validPetType, validCompanyId, validOwnerId)
            }
            assertEquals("Name cannot be empty", exception.message)
            verify(petDaoMock, never()).insertPet(validName, petTypeDog, validCompanyId, validOwnerId)
        }

        @Test
        fun `insertPet should throw exception if type is null`() {
            val exception = assertThrows<IllegalArgumentException> {
                petService.insertPet(validName, null, validCompanyId, validOwnerId)
            }
            assertEquals("Type cannot be empty", exception.message)
            verify(petDaoMock, never()).insertPet(validName, petTypeDog, validCompanyId, validOwnerId)
        }

        @Test
        fun `insertPet should throw exception if companyId is less than zero`() {
            val exception = assertThrows<IllegalArgumentException> {
                petService.insertPet(validName, validPetType, illegalCompanyId, validOwnerId)
            }
            assertEquals("Invalid companyId", exception.message)
            verify(petDaoMock, never()).insertPet(validName, petTypeDog, validCompanyId, validOwnerId)
        }

        @Test
        fun `insertPet should throw exception if ownerId is null or less than zero`() {
            val exception = assertThrows<IllegalArgumentException> {
                petService.insertPet(validName, validPetType, validCompanyId, null)
            }
            assertEquals("Invalid ownerId", exception.message)
            verify(petDaoMock, never()).insertPet(validName, petTypeDog, validCompanyId, validOwnerId)
        }

        @Test
        fun `insertPet should insert pet successfully`() {
            petService.insertPet(validName, validPetType, validCompanyId, validOwnerId)
            verify(petDaoMock).insertPet(validName, petTypeDog, validCompanyId, validOwnerId)
        }

        @Test
        fun `adoptPet should throw exception if petId is less than zero`() {
            val exception = assertThrows<IllegalArgumentException> {
                petService.adoptPet(illegalPetId, validOwnerId, validCompanyId)
            }
            assertEquals("Invalid petId", exception.message)
            verify(petDaoMock, never()).adoptPet(validPetId, validOwnerId, validCompanyId)
        }

        @Test
        fun `adoptPet should throw exception if ownerId is less than zero`() {
            val exception = assertThrows<IllegalArgumentException> {
                petService.adoptPet(validPetId, illegalOwnerId, validCompanyId)
            }
            assertEquals("Invalid ownerId", exception.message)
            verify(petDaoMock, never()).adoptPet(validPetId, validOwnerId, validCompanyId)
        }

        @Test
        fun `adoptPet should throw exception if companyId is less than zero`() {
            val exception = assertThrows<IllegalArgumentException> {
                petService.adoptPet(validPetId, validOwnerId, illegalCompanyId)
            }
            assertEquals("Invalid companyId", exception.message)
            verify(petDaoMock, never()).adoptPet(validPetId, validOwnerId, validCompanyId)
        }

        @Test
        fun `adoptPet should adopt pet successfully`() {
            petService.adoptPet(validPetId, validOwnerId, validCompanyId)

            verify(petDaoMock).adoptPet(validPetId, validOwnerId, validCompanyId)
        }




}