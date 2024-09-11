package com.hibob.academy.dao

import java.sql.Date

data class Example(val id: Long, val companyId: Long, val data: String)

data class Pet(
    val id: Int,
    val name: String,
    val type: String,
    val companyId: Long,
    val arrivalDate: Date
)

data class PetWithoutType(
    val id: Int,
    val name: String,
    val companyId: Long,
    val arrivalDate: Date
)

data class Owner(
    val id: Int,
    val name: String,
    val firstName: String?,
    val lastName: String?,
    val companyId: Long,
    val employeeId: String
)

enum class PetType() {
    DOG, CAT, BIRD, MOUSE
}

fun getPetType(petType: PetType) =
    when (petType) {
        PetType.DOG -> "Dog"
        PetType.CAT -> "Cat"
        PetType.BIRD -> "Bird"
        PetType.MOUSE -> "Mouse"
    }