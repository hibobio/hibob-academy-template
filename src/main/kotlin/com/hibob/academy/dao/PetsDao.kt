package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable
import com.hibob.academy.utils.asIs
import org.jooq.DSLContext
import org.springframework.stereotype.Component
import com.hibob.academy.utils.from
import org.jooq.Record
import org.jooq.RecordMapper
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

enum class PetType(val type: String) {
    DOG("dog"),
    CAT("cat"),
    BIRD("bird"),
    FISH("fish");
}

@Component
class PetDao(private val sql: DSLContext) {

    private val table = PetTable.instance

    private val petMapper = RecordMapper<Record, PetData> {
            record ->
        PetData(record[table.id],
            record[table.companyId],
            record[table.name],
            record[table.type],
            record[table.ownerId])
    }

    fun petsByType(companyId: Long, type: PetType) : List<PetData> {
        return sql.select(table.id, table.companyId, table.name, table.type, table.ownerId)
            .from(table)
            .where(table.companyId.eq(companyId))
            .and(table.type.eq(type.type))
            .fetch(petMapper)
    }

    fun insertPet(companyId: Long, name: String, type: String) {
        sql.insertInto(table)
            .set(table.companyId, companyId)
            .set(table.name, name)
            .set(table.type, type)
            .execute()
    }

    fun updatePet(petId: Int, ownerId: Long) {

    }
}
