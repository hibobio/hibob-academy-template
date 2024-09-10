package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.RecordMapper
import org.jooq.Record


class PetsDao(private val sql: DSLContext) {

    private val p = Pets.instance

    private val petMapper = RecordMapper<Record, PetData>
    { record ->
        PetData(
            record[p.name],
            record[p.type],
            record[p.companyId],
            record[p.dateOfArrival]
        )
    }
    enum class PetType {
        DOG,
        CAT
    }

    fun getAllPetsByType(type: PetType) : List<PetData> =
        sql.select()
        .from(p)
        .where(p.type.eq(getType(type)))
        .fetch(petMapper)

    fun getType(type: PetType): String {
        return when (type) {
            PetType.DOG -> "DOG"
            PetType.CAT -> "CAT"
        }
    }
}