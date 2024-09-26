package com.hibob.academy.filters

import com.hibob.academy.employeeFeedback.dao.Department
import com.hibob.academy.employeeFeedback.dao.EmployeeData
import com.hibob.academy.employeeFeedback.dao.Role
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import org.springframework.stereotype.Component
import com.hibob.academy.service.SessionService.Companion.SECRET_KEY
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.Provider


@Component
@Provider
class AuthenticationFilter : ContainerRequestFilter {
    companion object {
        private const val LOGIN_PATH = "api/employee/login"
        private const val COOKIE_NAME = "matan_name"
        const val EMPLOYEE = "employee"
    }

    private val logger = org.slf4j.LoggerFactory.getLogger(AuthenticationFilter::class.java)

    override fun filter(requestContext: ContainerRequestContext) {

        if (requestContext.uriInfo.path == LOGIN_PATH) return

        val jwtCookie = requestContext.cookies[COOKIE_NAME]?.value
        verify(jwtCookie, requestContext)
    }


    fun verify(cookie: String?, requestContext: ContainerRequestContext) {
        if (cookie == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build())
            return
        }
        try {
            val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(cookie)
                .body

            val employeeData = EmployeeData(
                id = (claims["id"] as Number).toLong(),
                role = Role.fromDatabaseValue(claims["role"] as String),
                companyId = (claims["companyId"] as Number).toLong(),
                department = Department.fromDatabaseValue(claims["department"] as String)
            )
            requestContext.setProperty(EMPLOYEE, employeeData)
        } catch (e: Exception) {
            logger.error(e.message, e)
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build())
        }
    }

}