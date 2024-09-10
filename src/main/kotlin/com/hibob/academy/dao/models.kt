package com.hibob.academy.dao

import java.sql.Date

data class Example(val id: Long, val companyId: Long, val data: String)

data class PetData(
    val name: String,
    val type: String,
    val companyId: Int,
    val arrivalDate: Date
)

data class OwnerData(
    val name: String?,
    val firstName: String?,
    val lastName: String?,
    val companyId: Int,
    val employeeId: Int
)