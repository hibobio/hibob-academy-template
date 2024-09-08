package com.hibob.academy.types

import java.util.*
import java.time.LocalDateTime
import java.util.UUID

data class Pets(
    val id: UUID = UUID.randomUUID(),
    var name: String, var type: String,
    var company_id: UUID,
    var date_of_arrival: Date
)

data class Owner(
    val id: UUID,
    val companyId: UUID,
    val employeeId: UUID,
    var name: String,
    var type: String,
    var dateOfArrival: LocalDateTime
)