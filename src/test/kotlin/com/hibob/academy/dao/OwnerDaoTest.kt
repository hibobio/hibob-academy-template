package com.hibob.academy.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

@BobDbTest
class OwnerDaoTest @Autowired constructor(private val sql: DSLContext){

    private val companyId:Long=8
    private val table=OwnerTable.instance
    private val dao = OwnerDao(sql)


    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(table).where(table.companyId.eq(companyId)).execute()
    }


    @Test
    fun `make a new owner`() {
        val ownerTest = OwnerData("matan",222,companyId)
        val pet1 = PetDataType(
            name = "Rex",
            type = PetType.Dog,
            dateOfArrival = LocalDate.of(2024, 9, 11),
            companyId = 1,
            ownerId = 101
        )

        val pet2 = PetDataType(
            name = "Whiskers",
            type = PetType.Cat,
            dateOfArrival = LocalDate.of(2024, 9, 12),
            companyId = 2,
            ownerId = 102
        )
        val pet3 =


        dao.createNewOwner(ownerTest)
        assertEquals(1, dao.getAllOwner().size)
    }
}