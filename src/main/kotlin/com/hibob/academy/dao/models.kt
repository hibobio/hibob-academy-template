package com.hibob.academy.dao

import java.time.LocalDate

data class Example(val id: Long, val companyId: Long, val data: String)

data class Pet(
    val id: Int,
    val name: String,
    val type: String,
    val companyId: String,
    val dateOfArrival: Long
)

data class Owner(
    val id: Int,
    val name: String,
    val companyId: Long,
    val employeeId: String
)