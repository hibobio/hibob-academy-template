package com.hibob.academy.filters

import com.hibob.academy.service.SessionService.Companion.SECRET_KEY
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.Provider
import org.springframework.stereotype.Component
import io.jsonwebtoken.Jwts


@Component
@Provider
class AuthenticationFilter : ContainerRequestFilter {
    companion object {
        const val cookieName = "Jwt"
        const val ignoreThisUrl = "api/login"
        const val employeeIdHeader = "X-Employee-Id"
        const val companyIdHeader = "X-Company-Id"    }

    override fun filter(requestContext: ContainerRequestContext) {

        if (requestContext.uriInfo.path == ignoreThisUrl) return

        val jwtCookie = requestContext.cookies[cookieName]?.value
        val claims = verifyAndExtractClaims(jwtCookie, requestContext)
        claims?.let{
            //TO DO: create a User object here and store that as a request attribute
            requestContext.headers.add(employeeIdHeader, it["employeeId"].toString())
            requestContext.headers.add(companyIdHeader, it["companyId"].toString())
        }
    }

    fun verifyAndExtractClaims(cookie: String?, requestContext: ContainerRequestContext): Map<String, String?>? {
        return cookie?.let {
            try {
                val claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(it)
                    .body

                mapOf(
                    "employeeId" to claims["employeeId"].toString(),
                    "companyId" to claims["companyId"].toString()
                )
            } catch (e: Exception) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build())
                null
            }
        }
    }

}