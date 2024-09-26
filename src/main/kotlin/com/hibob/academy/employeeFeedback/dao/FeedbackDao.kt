package com.hibob.academy.employeeFeedback.dao

import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class FeedbackDao(private val sql: DSLContext) {
    private val feedbackTable = FeedbackTable.instance
    private val employeeTable = EmployeeTable.instance
    private val feedbackMapper = RecordMapper<Record, FeedbackData> { record ->
        FeedbackData(
            record[feedbackTable.id],
            record[feedbackTable.feedback],
            record[feedbackTable.employeeId],
            record[feedbackTable.companyId],
            record[feedbackTable.date],
            FeedbackStatus.fromDatabaseValue(record[feedbackTable.status])
        )
    }

    fun viewAllFeedback(companyId: Long): List<FeedbackData> {
        return sql.select()
            .from(feedbackTable)
            .where(feedbackTable.companyId.eq(companyId))
            .fetch(feedbackMapper)
    }

    fun insertFeedback(feedback: Feedback, companyId: Long): Boolean {
        return sql.insertInto(feedbackTable)
            .set(feedbackTable.feedback, feedback.feedback)
            .set(feedbackTable.employeeId, feedback.employeeId)
            .set(feedbackTable.companyId, companyId)
            .set(feedbackTable.date, LocalDate.now())
            .set(feedbackTable.status, FeedbackStatus.UNREVIEWED.toDatabaseValue())
            .execute() > 0
    }

    fun updateFeedbackStatus(feedbackId: Long, companyId: Long, feedbackStatus: FeedbackStatus): Boolean {
        return sql.update(feedbackTable)
            .set(feedbackTable.status, feedbackStatus.toDatabaseValue())
            .where(feedbackTable.id.eq(feedbackId))
            .and(feedbackTable.companyId.eq(companyId))
            .execute() > 0
    }

    fun checkFeedbackStatus(feedbackId: Long, employeeId: Long, companyId: Long): String? {
        return sql.select(feedbackTable.status)
            .from(feedbackTable)
            .where(feedbackTable.companyId.eq(companyId))
            .and(feedbackTable.employeeId.eq(employeeId))
            .and(feedbackTable.id.eq(feedbackId))
            .fetchOne(feedbackTable.status)
    }

    fun getFeedbackByDate(companyId: Long, date: LocalDate): List<FeedbackData> {
        return sql.selectFrom(feedbackTable)
            .where(feedbackTable.companyId.eq(companyId))
            .and(feedbackTable.date.eq(date))
            .fetch(feedbackMapper)
    }

    fun getFeedbackByDepartment(companyId: Long, department: Department): List<FeedbackData> {
        return sql.select(feedbackTable)
            .from(feedbackTable)
            .join(employeeTable).on(feedbackTable.employeeId.eq(employeeTable.id))
            .where(feedbackTable.companyId.eq(companyId))
            .and(employeeTable.department.eq(department.toDatabaseValue()))
            .fetch(feedbackMapper)
    }

    fun getFeedbackByNullEmployeeId(companyId: Long): List<FeedbackData> {
        return sql.selectFrom(feedbackTable)
            .where(feedbackTable.companyId.eq(companyId))
            .and(feedbackTable.employeeId.isNull)
            .fetch(feedbackMapper)
    }


    fun getFeedbackByParams(companyId: Long, params: FeedbackQueryParams): List<FeedbackData> {
        val query = sql.select(
            feedbackTable.id,
            feedbackTable.feedback,
            feedbackTable.employeeId,
            feedbackTable.companyId,
            feedbackTable.date,
            feedbackTable.status
        )
            .from(feedbackTable)
            .leftJoin(employeeTable).on(feedbackTable.employeeId.eq(employeeTable.id))
            .where(feedbackTable.companyId.eq(companyId))

        params.department?.let {
            query.and(employeeTable.department.eq(it.name))
                .and(feedbackTable.employeeId.eq(employeeTable.id))
        }

        if (params.nullEmployeeId)
            query.and(feedbackTable.employeeId.isNull)

        params.date?.let {
            query.and(feedbackTable.date.eq(params.date))
        }

        return query.fetch(feedbackMapper)
    }


}