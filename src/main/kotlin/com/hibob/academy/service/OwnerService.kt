package com.hibob.academy.service

import com.hibob.academy.dao.OwnerDao
import com.hibob.academy.dao.OwnerData

class OwnerService(private val ownerDao : OwnerDao){

    fun getAllOwner(companyId:Long): List<OwnerData> = ownerDao.getAllOwner(companyId)

    fun createNewOwner(name:String,employeeId:Long,companyId: Long) = ownerDao.createNewOwner(name, employeeId, companyId)

    fun getOwnerByPetId(petId: Long, companyId: Long): OwnerData? = ownerDao.getOwnerByPetId(petId, companyId)
}