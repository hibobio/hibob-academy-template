package com.hibob.academy.dao

import java.time.LocalDate

data class Example(val id: Long, val companyId: Long, val data: String)

data class PetData(val ownerId: Long? , val petId: Long, val name: String, val type: String, val companyId: Long, val dateOfArrival: LocalDate)

data class OwnerData(val name: String, val companyId: Long, val employeeId: String)