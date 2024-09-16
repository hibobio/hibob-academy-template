package com.hibob.academy.service

import com.hibob.academy.dao.PetDao
import com.hibob.academy.dao.PetData
import com.hibob.academy.dao.PetType

class PetService(private val petDao : PetDao){

    fun getPetsByType(companyId:Long,type: PetType): List<PetData> {
        if(companyId < 0){
            throw IllegalArgumentException("Invalid companyId")
        }
        if(type.equals(null)){
            throw IllegalArgumentException("Invalid type")
        }
        return petDao.getPetsByType(companyId,type)
    }

    fun insertPet(name: String, type: PetType,companyId: Long ,ownerId:Long?) {
        if(name==""){
            throw IllegalArgumentException("Name cannot be empty")
        }
        if(type.equals(null)){
            throw IllegalArgumentException("Type cannot be empty")
        }
        if(companyId < 0){
            throw IllegalArgumentException("Invalid companyId")
        }
        if(ownerId == null || ownerId < 0){
            throw IllegalArgumentException("Invalid ownerId")
        }
        return petDao.insertPet(name, type,companyId,ownerId)
    }

    fun adoptPet(petId: Long, ownerId: Long,companyId :Long) {
        if(petId < 0){
            throw IllegalArgumentException("Invalid petId")
        }
        if(ownerId < 0){
            throw IllegalArgumentException("Invalid ownerId")
        }
        if(companyId < 0){
            throw IllegalArgumentException("Invalid companyId")
        }
        return petDao.adoptPet(petId,ownerId,companyId)
    }
}