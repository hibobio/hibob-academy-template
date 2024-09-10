package com.hibob.academy.dao

import java.time.LocalDate

data class Example(val id: Long, val companyId: Long, val data: String)

data class Pet(
    val id: Int,
    val name: String,
    val type: String,
    val companyId: Int,
    val arrivalDate: LocalDate
)

data class Owner(
    val id: Int,
    val name: String?,
    val firstName: String?,
    val lastName: String?,
    val companyId: Int,
    val employeeId: Int
)