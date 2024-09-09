package com.hibob.academy.resource

import com.hibob.academy.service.SessionService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.NewCookie
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody

@Controller
@Path("/Jwt")
class SessionApi(private val sessionService: SessionService) {

    @POST
    @Path("/Login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun login(@RequestBody user : User ): Response? {
        val cookie = NewCookie.Builder("Jwt").value(sessionService.createJwtToken(user)).build()
        return Response.ok().cookie(cookie).build()
    }

    @GET
    @Path("/GetUserName")
    fun getUserName() {
        //Just so i can call it from postman before and after the login (to test the filter)
    }

    data class User(
        val userName: String,
        val email: String,
        val isAdmin: Boolean
    )

}