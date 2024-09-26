package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@BobDbTest
class ResponseDaoTest @Autowired constructor(private val sql: DSLContext) {
    private val companyId: Long = 8L
    private val table = ResponseTable.instance
    private val dao = ResponseDao(sql)

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(table).execute()
    }


    @Test
    fun `insertResponse should be successful`() {
        val responseFeedback = ResponseFeedback(15L, "HI")
        val isInsert = dao.insertResponse(responseFeedback,companyId)
        assert(isInsert)

    }
}