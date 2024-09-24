package com.hibob.feedback.dao



import com.hibob.academy.utils.JooqTable

import org.jooq.DSLContext
import org.jooq.RecordMapper
import org.springframework.stereotype.Repository
import org.jooq.Record
import java.util.*


@Repository
class FeedbackDao(private val sql: DSLContext) {

    class FeedbackTable(tablename: String) : JooqTable(tablename) {
        val feedbackId = createUUIDField("id")
        val employeeId = createUUIDField("employee_id")
        val creationDate = createDateField("creation_date")
        val companyId = createUUIDField("company_id")
        val status = createVarcharField("status")
        val feedbackMessage = createVarcharField("feedback_message")

        companion object {
            val instance = FeedbackTable("feedback")
        }
    }


    private val feedbackTables = FeedbackTable.instance


    private val feedbackMapper = RecordMapper<Record, Feedback> { record ->
        Feedback(
            record[feedbackTables.feedbackId],
            record[feedbackTables.creationDate],
            record[feedbackTables.companyId],
            record[feedbackTables.feedbackMessage]
        )
    }


    fun createFeedback(feedback: Feedback, activeUser: ActiveUser): UUID {
        return sql.insertInto(feedbackTables)
            .set(feedbackTables.employeeId, activeUser.employeeId)
            .set(feedbackTables.companyId, feedback.companyId)
            .set(feedbackTables.status, Status.UNREVIEWED.toString())
            .set(feedbackTables.feedbackMessage, feedback.feedbackMessage)
            .returning(feedbackTables.feedbackId)
            .fetchOne()!![feedbackTables.feedbackId]
    }


    fun getFeedback(feedbackId: UUID, activeUser: ActiveUser): Feedback? {
        return sql.select()
            .from(feedbackTables)
            .where(feedbackTables.feedbackId.eq(feedbackId))
            .and(feedbackTables.companyId.eq(activeUser.companyId))
            .fetchOne(feedbackMapper)
    }
}
