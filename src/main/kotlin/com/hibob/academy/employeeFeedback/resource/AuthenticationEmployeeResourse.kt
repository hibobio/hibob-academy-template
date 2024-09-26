package com.hibob.academy.employeeFeedback.resource

import com.hibob.academy.employeeFeedback.dao.Employee
import com.hibob.academy.employeeFeedback.service.SessionEmployeeService
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.NewCookie
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller

@Controller
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/employee")
class AuthenticationEmployeeResourse(private val service: SessionEmployeeService) {

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun registerEmployee(employee: Employee): Response {
        val tokenJwt = service.createJWTToken(employee)
        val cookie = NewCookie.Builder("matan_name").value(tokenJwt).path("/api/").build()
        return Response.ok().cookie(cookie).build()
    }
}