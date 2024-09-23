package com.hibob.feedback.resource

import com.hibob.feedback.dao.*
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Component
import java.util.*
import jakarta.ws.rs.core.MediaType


@Component
@Path("/feedback")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class FeedbackResource(/*feedback Serrvice*/) {

    @POST
    @Path("/v1/create")
    fun createFeedback(feedbackInput: FeedbackInput): Response {
        val activeUser = 0
        //send the service active user and feedbackInput.
        return Response.ok().build() //TO DO CHANGE IT to return the output from the service.
    }

    @GET
    @Path("/v1/feedbackId/{feedbackId}")
    fun getFeedback(@PathParam("feedbackId") feedbackId: UUID): Response {
        val activeUser = 0
        //send the service active user and feedbackId.
        return Response.ok().build() //TO DO CHANGE IT to return the output from the service.
    }


}
