package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable
import com.hibob.academy.utils.asIs
import org.jooq.DSLContext
import org.springframework.stereotype.Component
import com.hibob.academy.utils.from
import org.jooq.Record
import org.jooq.RecordMapper
import java.time.LocalDate

enum class PetType(val type: String) {
    DOG("dog"),
    CAT("cat"),
    BIRD("bird"),
    FISH("fish");
}

@Component
class PetsDao(private val sql: DSLContext) {

    private val table = PetsTable.instance

    private val petMapper = RecordMapper<Record, PetData> {
            record ->
        PetData(record[table.name],
            record[table.type],
            record[table.companyId])
    }

    fun petsByType(type: PetType) : List<PetData> {
        return sql.select(table.name, table.dateOfArrival, table.companyId)
            .from(table)
            .where(table.type.eq(type.type))
            .fetch(petMapper)
    }

//    fun
}
