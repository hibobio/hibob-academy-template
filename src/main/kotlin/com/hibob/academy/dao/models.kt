package com.hibob.academy.dao

import java.time.LocalDate

data class Example(val id: Long, val companyId: Long, val data: String)

data class PetData(
    val id: Int,
    val companyId: Long,
    val name: String,
    val type: String,
    val ownerId: Long?
)

data class OwnerData(
    val id: Long,
    val name: String,
    val companyId: Long,
    val employeeId: String
)