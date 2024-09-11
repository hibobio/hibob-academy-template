package com.hibob.academy.dao

import java.time.LocalDate
import java.util.Date

data class Example(val id: Int, val companyId: Long, val data: String)

data class PetData(val name: String, val dateOfArrival:LocalDate, val companyId: Long,val ownerId:Long)

data class PetDataType(val name: String,val type: PetType,val dateOfArrival:LocalDate, val companyId: Long,val ownerId:Long)


data class OwnerData(val name: String, val employeeId:Long, val companyId: Long)

enum class PetType{
    Dog,
    Cat;
    fun toDatabaseValue(): String {
        return this.name.lowercase()  // Converts enum name to lowercase
    }
}