package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

@BobDbTest
class FeedbackDaoTest @Autowired constructor(private val sql: DSLContext) {
    private val companyId: Long = -8L
    private val feedbackTable = FeedbackTable.instance
    private val employeeTable = EmployeeTable.instance
    private val feedbackDao = FeedbackDao(sql)
    private val employeeDao = EmployeeDao(sql)

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(feedbackTable).where(feedbackTable.companyId.eq(companyId)).execute()
        sql.deleteFrom(employeeTable).where(employeeTable.companyId.eq(companyId)).execute()
    }

    @Test
    fun `viewAllFeedback should be successful`() {
        val listFeedback = listOf(
            Feedback("Hi", 5L),
            Feedback("By", null)
        )
        feedbackDao.insertFeedback(listFeedback[0], companyId)
        feedbackDao.insertFeedback(listFeedback[1], companyId)
        val allFeedback = feedbackDao.viewAllFeedback(companyId)
        assertEquals(listFeedback.size, allFeedback.size)

        for (i in listFeedback.indices) {
            assertEquals(listFeedback[i].feedback, allFeedback[i].feedback)
            assertEquals(listFeedback[i].employeeId, allFeedback[i].employeeId)
        }
    }

    @Test
    fun `insertFeedback should be successful`() {
        val feedback = Feedback("Hi", 5L)
        val isInsert = feedbackDao.insertFeedback(feedback, companyId)
        assert(isInsert)
    }

    @Test
    fun `updateFeedbackStatus should be successful`() {
        val feedback = Feedback("Hi", 5L)
        feedbackDao.insertFeedback(feedback, companyId)
        val allFeedback = feedbackDao.viewAllFeedback(companyId)
        val isUpdate = feedbackDao.updateFeedbackStatus(allFeedback[0].id, companyId, FeedbackStatus.REVIEWED)
        assert(isUpdate)
        val feedbackStatus = feedbackDao.checkFeedbackStatus(allFeedback[0].id, 5L, companyId)
        assertNotNull(feedbackStatus)
        assertEquals(feedbackStatus?.let { FeedbackStatus.fromDatabaseValue(it) }, FeedbackStatus.REVIEWED)
    }

    @Test
    fun `checkFeedbackStatus should be successful`() {
        val feedback = Feedback("Hi", 5L)
        feedbackDao.insertFeedback(feedback, companyId)
        val allFeedback = feedbackDao.viewAllFeedback(companyId)
        val feedbackStatus = feedbackDao.checkFeedbackStatus(allFeedback[0].id, 5L, companyId)
        assertNotNull(feedbackStatus)
        assertEquals(feedbackStatus?.let { FeedbackStatus.fromDatabaseValue(it) }, FeedbackStatus.UNREVIEWED)
    }


    @Test
    fun `checkFeedbackStatus should returns null`() {
        val feedback = Feedback("Hi", 5L)
        feedbackDao.insertFeedback(feedback, companyId)
        val allFeedback = feedbackDao.viewAllFeedback(companyId)
        val feedbackStatus = feedbackDao.checkFeedbackStatus(allFeedback[0].id, 5L, 1)
        assertNull(feedbackStatus)
    }

    //    @Test
//    fun `getFeedbackByDate should return feedback`() {
//        val feedback = Feedback("Hi", 5L)
//        dao.insertFeedback(feedback, companyId)
//        val listFeedback = dao.getFeedbackByDate(companyId, LocalDate.now())
//        assertEquals(feedback.feedback, listFeedback[0].feedback)
//        assertEquals(feedback.employeeId, listFeedback[0].employeeId)
//    }
//
//
//    @Test
//    fun `getFeedbackByNullEmployeeId should return feedback`() {
//        val feedback1 = Feedback("Hi", null)
//        val feedback2 = Feedback("By", 5L)
//        dao.insertFeedback(feedback1, companyId)
//        dao.insertFeedback(feedback2, companyId)
//        val listFeedback = dao.getFeedbackByNullEmployeeId(companyId)
//        assertEquals(listFeedback.size,1)
//        assertEquals(feedback1.feedback, listFeedback[0].feedback)
//        assertEquals(feedback1.employeeId, listFeedback[0].employeeId)
//    }
//
//    @Test
//    fun `getFeedbackByNullEmployeeId should return empty list`() {
//        val feedback1 = Feedback("Hi", 6L)
//        val feedback2 = Feedback("By", 5L)
//        dao.insertFeedback(feedback1, companyId)
//        dao.insertFeedback(feedback2, companyId)
//        val listFeedback = dao.getFeedbackByNullEmployeeId(companyId)
//        assertEquals(listFeedback.size,0)
//    }

    @Test
    fun `FeedbackQueryParams should return feedback by date`() {
        val feedback = Feedback("Hi", 5L)
        feedbackDao.insertFeedback(feedback, companyId)
        val feedbackQueryParams = FeedbackQueryParams(LocalDate.now(), null, true)
        val listFeedback = feedbackDao.getFeedbackByParams(companyId, feedbackQueryParams)
        assertEquals(feedback.feedback, listFeedback[0].feedback)
        assertEquals(feedback.employeeId, listFeedback[0].employeeId)
    }

    @Test
    fun `getFeedbackByDate should return feedback by null Employee Id`() {
        val feedback = Feedback("Hi", null)
        feedbackDao.insertFeedback(feedback, companyId)
        val feedbackQueryParams = FeedbackQueryParams(null, null, true)
        val listFeedback = feedbackDao.getFeedbackByParams(companyId, feedbackQueryParams)
        assertEquals(feedback.feedback, listFeedback[0].feedback)
        assertEquals(feedback.employeeId, listFeedback[0].employeeId)
    }

    @Test
    fun `getFeedbackByDate should return feedback by department`() {
        val newEmployee1 = NewEmployee("Mat", "Sab", Role.EMPLOYEE, companyId, Department.UX)
        employeeDao.insertEmployee(newEmployee1)
        val employee = employeeDao.getEmployee(Employee(newEmployee1.firstName, newEmployee1.lastName,newEmployee1.companyId))
        assertNotNull(employee)
        val feedback = Feedback("Hi", employee?.id)
        feedbackDao.insertFeedback(feedback, companyId)
        val feedbackQueryParams = FeedbackQueryParams(null, Department.UX, false)
        val listFeedback = feedbackDao.getFeedbackByParams(companyId, feedbackQueryParams)
        assertEquals(feedback.feedback, listFeedback[0].feedback)
        assertEquals(feedback.employeeId, listFeedback[0].employeeId)
    }

}