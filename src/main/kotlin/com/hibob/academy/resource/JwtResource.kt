package com.hibob.academy.resource

import com.hibob.academy.service.SessionService
import jakarta.ws.rs.Path
import org.springframework.stereotype.Controller
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.Produces
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.GET
import jakarta.ws.rs.core.NewCookie
import jakarta.ws.rs.core.Response
import com.hibob.academy.filters.AuthenticationFilter

data class User(val email: String, val userName: String, val isAdmin: Boolean)

@Controller
@Produces(MediaType.APPLICATION_JSON)
@Path("/jwt/users")
class JwtResource(private val sessionService: SessionService) {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    fun addUser(user: User): Response {
        val tokenJwt = sessionService.createJwtToken(user) // Assuming createJWTToken returns a JWT
        val cookie = NewCookie.Builder(AuthenticationFilter.COOKIE_NAME).value(tokenJwt).build() //Creating new cookie
        return Response.ok().cookie(cookie).build()
    }

    @GET
    @Path("/getUser")
    @Consumes(MediaType.APPLICATION_JSON)
    fun getUser(): Response {
        return Response.ok().entity("ok").build()
    }
}