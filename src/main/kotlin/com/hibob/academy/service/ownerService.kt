package com.hibob.academy.service

import com.hibob.academy.dao.Owner
import com.hibob.academy.dao.OwnerDao
import com.hibob.academy.dao.OwnerNoId
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class OwnerService(private val ownerDao: OwnerDao) {

    fun getOwners(companyId: Long): List<Owner> {
        return ownerDao.getOwners(companyId)
    }

    fun getOwnerByPetId(petId: UUID, companyId: Long): MutableList<Owner> {
        return ownerDao.getOwnerByPetId(petId, companyId)
    }

    fun createOwner(owner: OwnerNoId): UUID {
        return ownerDao.createOwner(owner)
    }

}