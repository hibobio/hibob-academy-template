package com.hibob.academy.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date
import com.hibob.academy.resource.User

import org.springframework.stereotype.Component

@Component
class SessionService {
    companion object {
        const val SECRET_KEY = "secjgfthgfth67ythgf657rtythfggfdfgdfasjdhsuytuytuyuttuuuutuytyuajfh3243hgasfssdfdfsdesrytftyr657ret"
    }
    val now = Date()
    fun createJwtToken(user: User): String {
        return Jwts.builder().setHeaderParam("typ", "JWT")
            .claim("email", user.email)
            .claim("username", user.userName)
            .claim("isAdmin", user.isAdmin)
            .setExpiration(Date(now.time + 60 * 60 * 24))
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact()
    }
}