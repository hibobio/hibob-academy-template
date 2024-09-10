package com.hibob.academy.dao

import com.hibob.academy.utils.asIs
import com.hibob.academy.utils.from
import org.jooq.DSLContext
import org.jooq.RecordMapper
import org.jooq.Record


class PetsDao(private val sql: DSLContext) {

    private val p = Pets.instance

    private val petMapper = RecordMapper<Record, PetData>
    { record ->
        PetData(
            record[],
            record[],

        )

    }


    private val type = "dog"





    fun getAllPetsByType() : List<Pets> {
        sql.select().from(p).where(p.type.eq(type)).fetch()
    }


    fun createExample(companyId: Long, data: String) {
        sql.insertInto(table).columns(table.companyId, table.data)
            .values(companyId, data).execute()
    }

    fun readExample(companyId: Long): Example? =
        sql.selectFrom(table)
            .where(table.companyId.eq(companyId))
            .fetch(exampleMapper()).first()

    fun exampleMapper() = ::Example.from(
        table.id.asIs(),
        table.companyId.asIs(),
        table.data.asIs(),
    )
}