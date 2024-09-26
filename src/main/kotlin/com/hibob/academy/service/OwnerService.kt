package com.hibob.academy.service

import com.hibob.academy.dao.OwnerDao
import com.hibob.academy.dao.OwnerData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OwnerService @Autowired constructor(private val ownerDao : OwnerDao){

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
        val owner= ownerDao.getOwnerByPetId(petId, companyId)
            ?: throw IllegalArgumentException("There is no owner with this id")
        return owner
    }
}