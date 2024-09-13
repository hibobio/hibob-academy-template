package com.hibob.academy.dao

import java.sql.Date
import java.util.*

data class Example(val id: Long, val companyId: Long, val data: String)

data class Owner(
    val id: UUID,
    val company_id: Long,
    val employee_id: String,
    val name: String
)

data class Pet(
    val id: UUID,
    val name: String,
    val type: String,
    val companyId: Long,
    val dateOfArrival: Date,
    val ownersId: UUID?
)

