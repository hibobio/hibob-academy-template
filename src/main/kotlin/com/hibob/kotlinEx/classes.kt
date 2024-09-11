package com.hibob.kotlinEx

import java.util.Date

data class Pet(val id: Int, val name: String, val firstname : String?, val lastname : String?,  val type: String, val companyId: Int, val dateOfArrival: Date)

data class Owner(val id: Int, val name: String, val firstname : String?, val lastname : String?, val companyId: Int, val employeeId: Int)