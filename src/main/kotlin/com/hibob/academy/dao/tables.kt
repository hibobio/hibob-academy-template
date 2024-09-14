package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable

class PetsTable(tableName: String = "pets") : JooqTable(tableName) {
    val petId = createBigIntField("id")
    val ownerId = createBigIntField("owner_id")
    val name = createVarcharField("name")
    val type = createVarcharField("type")
    val companyId = createBigIntField("company_id")
    val dateOfArrival = createLocalDateField("date_of_arrival")

    companion object {
        val instance = PetsTable()
    }
}

class OwnerTable(tableName: String = "owner") : JooqTable(tableName) {
    val ownerId = createBigIntField("id")
    val name = createVarcharField("name")
    val companyId = createBigIntField("company_id")
    val employeeId = createVarcharField("employee_id")

    companion object {
        val instance = OwnerTable()
    }
}

enum class PetType {
    DOG,
    CAT;
}

fun getType(type: PetType): String {
    return when (type) {
        PetType.DOG -> "DOG"
        PetType.CAT -> "CAT"
    }
}
