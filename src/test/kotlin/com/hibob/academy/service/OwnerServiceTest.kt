package com.hibob.academy.service

import com.hibob.academy.dao.Owner
import com.hibob.academy.dao.OwnerDao
import com.hibob.academy.dao.OwnerNoId
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import java.util.*

class OwnerServiceTest{
    private val ownerDaoMock = mock<OwnerDao>()
    private val service = OwnerService(ownerDaoMock)

    @Test
    fun `get owners`(){
        val owner = Owner(UUID.randomUUID(), 9, "AA", "bob")
        whenever(ownerDaoMock.getOwners(9)).thenReturn(listOf(owner))
        assertEquals(listOf(owner), service.getOwners(9))
    }

    @Test
    fun `owner by pet id`(){
        val owner = Owner(UUID.randomUUID(), 9, "AA", "bob")
        val petId = UUID.randomUUID()
        whenever(ownerDaoMock.getOwnerByPetId(petId, 9)).thenReturn(mutableListOf(owner))
        assertEquals(mutableListOf(owner), service.getOwnerByPetId(petId, 9))
    }

    @Test
    fun `create owner`(){
        val ownerId = UUID.randomUUID()
        val ownerNoId = OwnerNoId(9, "AA", "bob")
        whenever(ownerDaoMock.createOwner(OwnerNoId(9, "AA", "bob"))).thenReturn(ownerId)
        assertEquals(service.createOwner(ownerNoId), ownerId)
    }
}