package com.hibob.academy.filters

import com.hibob.academy.service.SessionService.Companion.SECRET_KEY
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.Provider
import org.springframework.stereotype.Component


@Component
@Provider
class AuthenticationFilter : ContainerRequestFilter {

    override fun filter(requestContext: ContainerRequestContext){

        if(requestContext.uriInfo.path == "Jwt/Login") return

        val jwtCookie = requestContext.cookies["Jwt"]?.value
        val claim = verify(jwtCookie)

        if( claim == null){
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build())
        }

    }

    fun verify(cookie: String?): Jws<Claims>? =
        cookie?.let{
            try{
                Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(it)
            } catch (e: Exception){
                null
            }
        }

}