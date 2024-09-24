package com.hibob.academy.employeeFeedback.dao

import org.jooq.RecordMapper
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.stereotype.Component

@Component
class FeedbackDao(private val sql: DSLContext) {
    private val feedbackTable = FeedbackTable.instance

    private val feedbackMapper = RecordMapper<Record, FeedbackOut>
    { record ->
        FeedbackOut(
            record[feedbackTable.id],
            record[feedbackTable.employeeId],
            record[feedbackTable.content],
            enumValueOf<FeedbackStatus>(record[feedbackTable.status]),
            record[feedbackTable.companyId],
            record[feedbackTable.date]
        )
    }

    fun addFeedback(feedback: FeedbackIn): Long {
        val id = sql.insertInto(feedbackTable)
            .set(feedbackTable.employeeId, feedback.employeeId)
            .set(feedbackTable.content, feedback.content)
            .set(feedbackTable.status, feedback.status.name)
            .set(feedbackTable.companyId, feedback.companyId)
            .returning(feedbackTable.id)
            .fetchOne()
        return id?.get(feedbackTable.id) ?: throw RuntimeException("Failed to insert feedback")
    }

    fun getFeedbackById(id: Long, companyId: Long): FeedbackOut {
        return sql.select(feedbackTable.id, feedbackTable.employeeId, feedbackTable.content, feedbackTable.status, feedbackTable.companyId, feedbackTable.date)
            .from(feedbackTable)
            .where(feedbackTable.id.eq(id))
            .and(feedbackTable.companyId.eq(companyId))
            .fetchOne(feedbackMapper) ?: throw RuntimeException("Failed to fetch feedback")
    }

    fun getFeedbackStatus(id: Long, companyId: Long): String {
        return  sql.select(feedbackTable.status)
            .where(feedbackTable.id.eq(id))
            .and(feedbackTable.companyId.eq(companyId))
            .fetchOne()
            ?.getValue(feedbackTable.status) ?: throw RuntimeException("companyId or feedbackId does not exist in the system")
    }

    fun deleteTable(companyId: Long) {
        sql.deleteFrom(feedbackTable)
            .where(feedbackTable.companyId.eq(companyId))
            .execute()
    }
}