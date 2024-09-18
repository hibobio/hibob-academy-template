package com.hibob.academy.service

import com.hibob.academy.dao.PetData
import com.hibob.academy.dao.PetDataInsert
import com.hibob.academy.dao.PetType
import com.hibob.academy.dao.PetsDao

class PetsService(private val petDao: PetsDao) {

    fun getAllPetsByType(type: PetType, companyId: Long): List<PetData> {
        return petDao.getAllPetsByType(type, companyId)
    }

    fun createPet(petData: PetDataInsert) {
        petDao.createPet(petData)
    }

    fun updatePetOwnerId(petId: Long, ownerId: Long, companyId: Long) {
        petDao.updatePetOwnerId(petId, ownerId, companyId)
    }
}