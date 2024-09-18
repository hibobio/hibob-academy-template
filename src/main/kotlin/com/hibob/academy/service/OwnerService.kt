package com.hibob.academy.service

import com.hibob.academy.dao.*
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException

@Component
class OwnerService(private val ownerDao: OwnerDao) {

    fun getAllOwners(companyId: Long): List<OwnerData> {
        return ownerDao.getAllOwners(companyId)
    }

    fun createOwnerIfNotExists(ownerData: OwnerDataInsert) {
        val checkCreate = ownerDao.createOwnerIfNotExists(ownerData)
        if (checkCreate.equals(0)) throw IllegalArgumentException("Owner already exists")
    }

    fun getOwnerByPetId(petId: Long, companyId: Long): OwnerData {
        return ownerDao.getOwnerByPetId(petId, companyId) ?: throw IllegalArgumentException("the information you entered does not match the database")
    }
}