package com.hibob.feedback.dao

import org.junit.jupiter.api.Test
import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import java.sql.Date
import java.time.LocalDate
import java.util.*

@BobDbTest
class FeedbackDaoTest @Autowired constructor(private val sql: DSLContext) {
    private val feedbackDao = FeedbackDao(sql)
    private val feedbackTable = FeedbackDao.FeedbackTable.instance
    private val companyId = UUID.randomUUID()

    private val feedback =
        FeedbackInput("Feedback for testing!", false)
    private val feedback1 =
        FeedbackInput("Another feedback for testing!", false)
    private val feedback2 = FeedbackInput("Another one", false)
    private val activeUser = ActiveUser(UUID.randomUUID(), companyId)

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(feedbackTable).where(feedbackTable.companyId.eq(companyId)).execute()
    }

    @Test
    fun `create should create a feedback and return his id`() {
        val feedbackId = feedbackDao.createFeedback(feedback, activeUser)
        assertTrue(feedbackId is UUID)
    }

    @Test
    fun `same employee can create multiple feedbacks`() {
        val feedbackId = feedbackDao.createFeedback(feedback, activeUser)
        val feedbackId1 = feedbackDao.createFeedback(feedback1, activeUser)
        val feedbackId2 = feedbackDao.createFeedback(feedback2, activeUser)
        assertTrue(feedbackId is UUID)
        assertTrue(feedbackId1 is UUID)
        assertTrue(feedbackId2 is UUID)
    }

    @Test
    fun `create and the get should return feedback`() {
        val feedbackId = feedbackDao.createFeedback(feedback, activeUser)
        assertEquals(Feedback(id = feedbackId, Date.valueOf(LocalDate.now()), companyId, feedback.feedbackMessage), feedbackDao.getFeedback(feedbackId, activeUser))
    }

    @Test
    fun `get where no feedback with that ID return null`() {
        assertEquals(null, feedbackDao.getFeedback(UUID.randomUUID(), activeUser))
    }
}