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
        const val ignoreThisUrl = "jwt/login"
    }

    override fun filter(requestContext: ContainerRequestContext) {

        if (requestContext.uriInfo.path == ignoreThisUrl) return

        val jwtCookie = requestContext.cookies[cookieName]?.value
        verify(jwtCookie, requestContext)
    }

    fun verify(cookie: String?, requestContext: ContainerRequestContext) =
        cookie?.let {
            try {
                Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(it)
            } catch (e: Exception) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build())
            }
        }

}