package com.hibob.academy.resource

import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.Response
import com.hibob.academy.service.SessionService
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.NewCookie
import org.springframework.stereotype.Controller

data class User(val email: String,val name: String, val isAdmin: Boolean)

@Controller
@Produces(MediaType.APPLICATION_JSON)
@Path("/jwt/users")
class AuthenticationResourse(private val service: SessionService) {
      //AuthenticationResource
    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun addNewUser(newUser: User): Response {
        val tokenJwt = service.createJWTToken(newUser)
        val cookie = NewCookie.Builder("matan_name").value(tokenJwt).build()//Creating new cookie
        return Response.ok().cookie(cookie).build()
    }

    @Path("/getAllUsers")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    fun getAllUsers(): Response {
        return Response.ok().entity("Yessssss").build()
    }
}