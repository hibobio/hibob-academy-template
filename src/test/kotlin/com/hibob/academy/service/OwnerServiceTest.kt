package com.hibob.academy.service

import com.hibob.academy.dao.OwnerDao
import com.hibob.academy.dao.OwnerData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class OwnerServiceTest{
    private val ownerDaoMock = mock<OwnerDao>{}
    private val ownerService = OwnerService(ownerDaoMock)
    private val validCompanyId = 8L
    private val illegalCompanyId = -1L
    private val validEmployeeId= 5L
    private val illegalEmployeeId= -4L
    private val validName = "Matan"
    private val emptyName = ""
    private val validPetId = 10L
    private val illegalPetId = -15L


    @Test
    fun `getAllOwner should throw exception if companyId less than zero `() {
        val exception = assertThrows<IllegalArgumentException> {
            ownerService.getAllOwner(illegalCompanyId)
        }
        assertEquals("Company id must be greater than 0", exception.message)
        verify(ownerDaoMock,never()).getAllOwner(illegalCompanyId)
    }

    @Test
    fun `getAllOwner should return all owner `() {
        val expectedOwners = listOf(
            OwnerData(1,"Owner 1",20,8),
            OwnerData(2,  "Owner 2",21,8)
        )

        whenever(ownerDaoMock.getAllOwner(validCompanyId)).thenReturn(expectedOwners)

        val actualOwners = ownerService.getAllOwner(validCompanyId)

        assertEquals(expectedOwners, actualOwners)
        verify(ownerDaoMock).getAllOwner(validCompanyId)
    }

    @Test
    fun `createNewOwner should throw exception if name is empty`() {
        val exception = assertThrows<IllegalArgumentException> {
            ownerService.createNewOwner(emptyName, validEmployeeId, validCompanyId)
        }
        assertEquals("Name must not be empty", exception.message)
        verify(ownerDaoMock, never()).createNewOwner(validName, validEmployeeId , validCompanyId)
    }



    @Test
    fun `createNewOwner should throw exception if employeeId is less than zero`() {
        val exception = assertThrows<IllegalArgumentException> {
            ownerService.createNewOwner(validName, illegalEmployeeId, validCompanyId)
        }
        assertEquals("Employee id must be greater than 0", exception.message)
        verify(ownerDaoMock, never()).createNewOwner(validName, validEmployeeId , validCompanyId)
    }

    @Test
    fun `createNewOwner should throw exception if companyId is less than zero`() {
        val exception = assertThrows<IllegalArgumentException> {
            ownerService.createNewOwner(validName, validEmployeeId, illegalCompanyId)
        }
        assertEquals("Company id must be greater than 0", exception.message)
        verify(ownerDaoMock, never()).createNewOwner(validName, validEmployeeId , validCompanyId)
    }

    @Test
    fun `createNewOwner should create new owner successfully`() {
        ownerService.createNewOwner(validName, validEmployeeId, validCompanyId)
        verify(ownerDaoMock).createNewOwner(validName, validEmployeeId, validCompanyId)
    }


    @Test
    fun `getOwnerByPetId should throw exception if petId is less than zero`() {
        val exception = assertThrows<IllegalArgumentException> {
            ownerService.getOwnerByPetId(illegalPetId, validCompanyId)
        }
        assertEquals("Pet id must be greater than 0", exception.message)
        verify(ownerDaoMock, never()).getOwnerByPetId(validPetId, validCompanyId)
    }

    @Test
    fun `getOwnerByPetId should throw exception if companyId is less than zero`() {
        val exception = assertThrows<IllegalArgumentException> {
            ownerService.getOwnerByPetId(validPetId, illegalCompanyId)
        }
        assertEquals("Company id must be greater than 0", exception.message)
        verify(ownerDaoMock, never()).getOwnerByPetId(validPetId, validCompanyId)
    }

    @Test
    fun `getOwnerByPetId should return owner data successfully`() {
        val expectedOwner = OwnerData(1,"Owner 1",20,8)
        whenever(ownerDaoMock.getOwnerByPetId(validPetId, validCompanyId)).thenReturn(expectedOwner)

        val actualOwner = ownerService.getOwnerByPetId(validPetId, validCompanyId)

        assertEquals(expectedOwner, actualOwner)
        verify(ownerDaoMock).getOwnerByPetId(validPetId, validCompanyId)
    }
}