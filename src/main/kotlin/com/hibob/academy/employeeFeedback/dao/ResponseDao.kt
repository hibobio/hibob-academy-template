package com.hibob.academy.employeeFeedback.dao

import org.jooq.DSLContext
import java.time.LocalDate

class ResponseDao(private val sql: DSLContext) {
    private val responseTable = ResponseTable.instance


    fun insertResponse(responseFeedback: ResponseFeedback, employeeId: Long): Boolean {
        return sql.insertInto(responseTable)
            .set(responseTable.feedbackId, responseFeedback.feedbackId)
            .set(responseTable.response, responseFeedback.response)
            .set(responseTable.employeeId, employeeId)
            .set(responseTable.date, LocalDate.now())
            .execute() > 0
    }

}