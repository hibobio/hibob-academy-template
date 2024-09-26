package com.hibob.academy.employeeFeedback.service

import com.hibob.academy.employeeFeedback.dao.Feedback
import com.hibob.academy.employeeFeedback.dao.FeedbackDao
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class FeedbackServiceTest {
    private val feedbackDaoMock = mock<FeedbackDao> {}
    private val feedbackService = FeedbackService(feedbackDaoMock)
    private val companyId: Long = 8L


    @Test
    fun `viewAllFeedback should be successful`() {
        whenever(feedbackDaoMock.viewAllFeedback(companyId)).thenReturn(any())
        feedbackService.viewAllFeedback(companyId)
        verify(feedbackDaoMock).viewAllFeedback(companyId)
    }

    @Test
    fun `insertFeedback should be successful`() {
        val feedback = Feedback("The feedback is ok", null)
        whenever(feedbackDaoMock.insertFeedback(feedback,companyId)).thenReturn(true)
        feedbackService.insertFeedback(feedback,companyId)
        verify(feedbackDaoMock).insertFeedback(feedback,companyId)
    }


}