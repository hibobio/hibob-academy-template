package com.hibob.academy.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.random.Random

@BobDbTest
class PetDaoTest @Autowired constructor(private val sql: DSLContext)  {

    private val dao = PetDao(sql)
    val companyId = Random.nextLong()
    val table = PetTable.instance

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(table).where(table.companyId.eq(companyId)).execute()
    }

    @Test
    fun `inserting pet test`() {
        val pet = PetData(1,companyId, "Murphy", "dog", 1)
        dao.insertPet(pet)
        val petsList = dao.petsByType(PetType.DOG)
        assertEquals(1, petsList.size)
        assertEquals(PetData(1,companyId, "Murphy", "dog", 1), petsList.get(0))
    }
}