package com.hibob.academy.service

import com.hibob.academy.resource.SessionApi.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@Component
class SessionService {
    companion object{
        const val SECRET_KEY = "ndjiaqsfghouhm24vvycgjhfuytuytgiyusasfdghjkhgfdfgyuiuytrfftyuiuytrtyuihttixicuvb785v68568ig5buitui8onkgy7498cqdehqiwfhbui5p3hqugo5hr"
    }

 com    fun createJwtToken(user: User): String {
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .claim("email", user.email)
            .claim("user name", user.userName)
            .claim("isAdmin", user.isAdmin)
            .setExpiration(Date.from(LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC)))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact()
    }

}