package com.hibob.academy.dao

import java.time.LocalDate


data class Example(val id: Int, val companyId: Long, val data: String)

data class PetData(val id: Long,val name: String,val type: PetType, val dateOfArrival:LocalDate, val companyId: Long,val ownerId:Long?)

data class OwnerData(val id:Long,val name: String, val employeeId:Long, val companyId: Long)

enum class PetType{
    DOG,
    CAT;
    fun toDatabaseValue(): String {
        return this.name.lowercase()  // Converts enum name to lowercase
    }
    companion object {
        fun fromDatabaseValue(value: String): PetType {
            return valueOf(value.uppercase())  // Converts the string to uppercase to match enum names
        }
    }

}