package com.hibob.academy.resource

import com.hibob.academy.filters.AuthenticationFilter
import com.hibob.academy.service.SessionService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.NewCookie
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import java.util.UUID

@Controller
@Path("/api")
class SessionApi(private val sessionService: SessionService) {

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun login(@RequestBody user: User): Response? {
        val cookie =
            NewCookie.Builder(AuthenticationFilter.cookieName).value(sessionService.createJwtToken(user)).path("/api/").build()
        return Response.ok().cookie(cookie).build()
    }

    data class User(
        val employeeId: UUID,
        val companyId: UUID
    )

}