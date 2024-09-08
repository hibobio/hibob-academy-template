package com.hibob.kotlinEx

data class Pet(val id: Int, val name: String, val type: String, val companyId: Int, val dateOfArrival: Int)

data class Owner(val id: Int, val name: String, val companyId: Int, val employeeId: Int)