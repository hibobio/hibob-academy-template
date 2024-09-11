package com.hibob.academy.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import kotlin.random.Random

@BobDbTest
class PetDaoTest @Autowired constructor(private val sql: DSLContext) {

    private val dao = PetDao(sql)
    val companyId = Random.nextLong()
    val table = PetTable.instance

    @Test
    fun selectAll() {
        sql.select(*table.fields())
    }

    @Test
    fun getPets() {
        sql.select(table.name, table.companyId, table.dateOfArrival)
            .from(table)
            .where(table.type.eq(getPetType(PetType.DOG)))
            .fetch()

        println("${table.name}, ${table.companyId}, ${table.dateOfArrival}")
    }
}