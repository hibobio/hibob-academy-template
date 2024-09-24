package com.hibob.academy.employeeFeedback.service

import com.hibob.academy.employeeFeedback.dao.Feedback
import com.hibob.academy.employeeFeedback.dao.FeedbackDao
import com.hibob.academy.employeeFeedback.dao.FeedbackData
import com.hibob.academy.employeeFeedback.dao.Role
import jakarta.ws.rs.BadRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FeedbackService @Autowired constructor(private val feedbackDao: FeedbackDao) {

    fun viewAllFeedback(companyId: Long): List<FeedbackData> {
        return feedbackDao.viewAllFeedback(companyId)
    }

    fun insertFeedback(feedback: Feedback) {
        feedbackDao.insertFeedback(feedback)
    }
}