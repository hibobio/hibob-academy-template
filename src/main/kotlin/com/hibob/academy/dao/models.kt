package com.hibob.academy.dao

import java.math.BigInteger
import java.sql.Date

data class Example(val id: Long, val companyId: Long, val data: String)

data class PetData(
    val name: String,
    val type: String,
    val companyId: BigInteger,
    val arrivalDate: Date,
)

data class OwnerData(
    val name: String?,
    val employeeId: String,
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
