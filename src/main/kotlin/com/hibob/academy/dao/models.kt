package com.hibob.academy.dao

import java.time.LocalDate

data class Example(val id: Long, val companyId: Long, val data: String)

data class PetData(val id: Long, val ownerId: Long?, val name: String, val type: PetType, val companyId: Long, val dateOfArrival: LocalDate)

data class PetDataInsert(val ownerId: Long?, val name: String, val type: PetType, val companyId: Long)

data class OwnerData(val id: Long, val name: String, val companyId: Long, val employeeId: String)

data class OwnerDataInsert(val name: String, val companyId: Long, val employeeId: String)

enum class PetType {
    DOG,
    CAT;
}