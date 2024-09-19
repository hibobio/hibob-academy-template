package com.hibob.academy.service

import com.hibob.academy.dao.*
import jakarta.ws.rs.BadRequestException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*

class OwnerServerTest {
    private val ownerDao = mock<OwnerDao>()
    private val ownerService = OwnerService(ownerDao)

    @Test
    fun `getAllOwners should return list of owners`() {
        val companyId = 1L
        val owner1 = OwnerData(id = 1L, name = "chezi", companyId, employeeId = "1")
        val owner2 = OwnerData(id = 1L, name = "chezi", companyId, employeeId = "2")
        val listOfOwner = listOf(owner1, owner2)

        whenever(ownerDao.getAllOwners(companyId)).thenReturn(listOfOwner)

        assertEquals(listOfOwner, ownerService.getAllOwners(companyId))
    }

    @Test
    fun `createOwnerIfNotExists should call DAO to create owner`() {
        val owner1 = OwnerDataInsert(name = "chezi", companyId = 1L, employeeId = "1")

        whenever(ownerDao.createOwnerIfNotExists(owner1)).thenReturn(1)

        ownerService.createOwnerIfNotExists(owner1)

        verify(ownerDao).createOwnerIfNotExists(owner1)
    }

    @Test
    fun `createOwnerIfNotExists should throw exception when we have the same owner`() {
        val companyId = 1L
        val owner1 = OwnerDataInsert(name = "chezi", companyId, employeeId = "1")

        whenever(ownerDao.createOwnerIfNotExists(owner1)).thenReturn(0)

        val exception = assertThrows<BadRequestException> {
            ownerService.createOwnerIfNotExists(owner1)
        }

        assertEquals("Owner already exists" ,exception.message)
    }

    @Test
    fun `getOwnerByPetId should return owner when exists`() {
        val companyId = 1L
        val petId = 1L
        val owner1 = OwnerData(id = 1L, name = "chezi", companyId, employeeId = "1")

        whenever(ownerDao.getOwnerByPetId(petId, companyId)).thenReturn(owner1)

        assertEquals(owner1, ownerService.getOwnerByPetId(petId, companyId))
    }

    @Test
    fun `getOwnerByPetId should throw exception when owner not found`() {
        val companyId = 1L
        val petId = 1L

        whenever(ownerDao.getOwnerByPetId(petId, companyId)).thenReturn(null)

        val exception = assertThrows<BadRequestException> {
            ownerService.getOwnerByPetId(petId, companyId)
        }

        assertEquals("the information you entered does not match the database", exception.message)
    }

    @Test
    fun `getOwnerByPetId should throw exception when pet not found`() {
        val companyId = 1L
        val petId = 1L

        whenever(ownerDao.getOwnerByPetId(petId, companyId)).thenReturn(null)

        val exception = assertThrows<BadRequestException> {
            ownerService.getOwnerByPetId(petId, companyId)
        }

        assertEquals("the information you entered does not match the database", exception.message)
    }
}