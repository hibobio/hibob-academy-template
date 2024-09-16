package com.hibob.academy.service

import com.hibob.academy.dao.OwnerDao
import com.hibob.academy.dao.OwnerData

class OwnerService(private val ownerDao : OwnerDao){

    fun getAllOwner(companyId:Long): List<OwnerData> {
        if(companyId < 0){
            throw IllegalArgumentException("Company id must be greater than 0")
        }
        return ownerDao.getAllOwner(companyId)
    }

    fun createNewOwner(name:String,employeeId:Long,companyId: Long) {
        if(name == ""){
            throw IllegalArgumentException("Name must not be empty")
        }
        if(employeeId < 0){
            throw IllegalArgumentException("Employee id must be greater than 0")
        }
        if(companyId < 0){
            throw IllegalArgumentException("Company id must be greater than 0")
        }
        return ownerDao.createNewOwner(name, employeeId, companyId)
    }

    fun getOwnerByPetId(petId: Long, companyId: Long): OwnerData? {
        if(petId < 0){
            throw IllegalArgumentException("Pet id must be greater than 0")
        }
        if(companyId < 0){
            throw IllegalArgumentException("Company id must be greater than 0")
        }
        return ownerDao.getOwnerByPetId(petId, companyId)
    }
}