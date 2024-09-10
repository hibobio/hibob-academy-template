package com.hibob.academy.dao

import java.util.*

data class Example(val id: Long, val companyId: Long, val data: String)

data class PetData(val name: String, val type: String, val companyId: Long, val dateOfArrival: Date)
data class OwnerData(val name: String, val companyId: Long, val employeeId: Long)
