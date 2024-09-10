package com.hibob.academy.dao

import org.jooq.Record
import org.jooq.impl.DSL
import java.time.LocalDate

data class Pet(
    val id: Int,
    val name: String,
    val type: String,
    val companyId: Long,
    val dateOfArrival: LocalDate
)

data class Owner(
    val id: Int,
    val name: String,
    val companyId: Long,
    val employeeId: Int
)


fun mapToPet(record: Record): Pet {
    return Pet(
        id = record.get(DSL.field("id", Int::class.java)) ?: 0,
        name = record.get(DSL.field("name", String::class.java)) ?: "",
        type = record.get(DSL.field("type", String::class.java)) ?: "",
        companyId = record.get(DSL.field("company_id", Long::class.java)) ?: 0L,
        dateOfArrival = record.get(DSL.field("date_of_arrival", LocalDate::class.java)) ?: LocalDate.now()
    )
}

fun mapToOwner(record: Record): Owner {
    return Owner(
        id = record.get(DSL.field("id", Int::class.java)) ?: 0,
        name = record.get(DSL.field("name", String::class.java)) ?: "",
        companyId = record.get(DSL.field("company_id", Long::class.java)) ?: 0L,
        employeeId = record.get(DSL.field("employee_id", Int::class.java)) ?: 0
    )
}
