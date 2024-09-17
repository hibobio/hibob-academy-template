package com.hibob.academy.dao

import java.sql.Date
import java.util.*

data class Example(val id: Long, val companyId: Long, val data: String)

data class Owner(
    val id: UUID,
    val companyId: Long,
    val employeeId: String,
    val name: String
)

data class OwnerNoId(
    val companyId: Long,
    val employeeId: String,
    val name: String
)

data class Pet(
    val id: UUID,
    val name: String,
    val type: String,
    val companyId: Long,
    val dateOfArrival: Date,
    val ownerId: UUID?
)

data class PetNoId(
    val name: String,
    val type: String,
    val companyId: Long,
    val dateOfArrival: Date,
    val ownerId: UUID?
)

