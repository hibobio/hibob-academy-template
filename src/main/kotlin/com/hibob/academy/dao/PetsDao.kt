package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable
import com.hibob.academy.utils.asIs
import org.jooq.DSLContext
import org.springframework.stereotype.Component
import com.hibob.academy.utils.from

enum class PetType(val type: String) {
    DOG("dog"),
    CAT("cat"),
    BIRD("bird"),
    FISH("fish");
}


@Component
class PetsDao(private val sql: DSLContext) {

    private val table = PetsTable.instance

    fun petsByType(type: PetType) : List<Pet> {
        return sql.select(table.name, table.date_of_arrival, table.companyId)
            .from(table)
            .where(table.type.eq(type.type))
            .fetch().map { record3 -> mapToPet(record3) }
    }
}
