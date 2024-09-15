package com.hibob.academy.service

import com.hibob.academy.dao.PetDao
import com.hibob.academy.dao.PetData
import com.hibob.academy.dao.PetType

class PetService(private val petDao : PetDao){

    fun getPetsByType(companyId:Long,type: PetType): List<PetData> = petDao.getPetsByType(companyId,type)

    fun insertPet(name: String, type: PetType,companyId: Long ,ownerId:Long?) = petDao.insertPet(name, type,companyId,ownerId)

    fun adoptPet(petId: Long, ownerId: Long,companyId :Long) = petDao.adoptPet(petId,ownerId,companyId)
}