package com.hibob.restAPI

import java.time.LocalDate
import java.time.LocalDateTime

data class Pet(val id: Int, val name: String, val type: String, val companyId: Int, val arrivalDate: LocalDate)

data class Owner(val id: Int, val name: String, val companyId: Int, val employeeId: Int)

