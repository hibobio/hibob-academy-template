package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.utils.BobDbTest
import org.junit.jupiter.api.Test
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.junit.jupiter.api.*

@BobDbTest
class FeedbackDaoTest @Autowired constructor(private val sql: DSLContext) {

    private val feedbackDao = FeedbackDao(sql)

    private val companyId = 1L
    val feedback1 = FeedbackIn(employeeId = 1L, content = "Excellent work", status = FeedbackStatus.UNREVIEWED, companyId)

    @Test
    fun `add feedback and verify insertion`() {
        val feedbackId = feedbackDao.addFeedback(feedback1)
        val returnedFeedback = feedbackDao.getFeedbackById(feedbackId, companyId)

        val expectedResult = FeedbackOut(feedbackId, feedback1.employeeId, feedback1.content, feedback1.status, companyId, returnedFeedback.date)

        assertEquals(expectedResult, returnedFeedback)
    }

    @Test
    fun `get feedback throws exception when no feedback exists`() {

        val exception = assertThrows<RuntimeException> {
            feedbackDao.getFeedbackById(999L, companyId)
        }

        assertEquals("Failed to fetch feedback", exception.message)
    }

    @Test
    fun `get feedback status when feedback exists`() {
        val feedbackId1 = feedbackDao.addFeedback(feedback1)

        val status = feedbackDao.getFeedbackStatus(feedbackId1, companyId)

        assertEquals("UNREVIEWED", status)
    }

    @Test
    fun `get feedback status throws exception when feedback does not exist`() {
        val exception = assertThrows<RuntimeException> {
            feedbackDao.getFeedbackStatus(999L, companyId)
        }
        assertEquals("companyId or feedbackId does not exist in the system", exception.message)
    }

    @BeforeEach
    @AfterEach
    fun cleanup() {
        feedbackDao.deleteTable(companyId)
    }
}