package com.hibob.academy.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.Date
import com.hibob.academy.resource.User

@Component
class SessionService {
    companion object {
        const val SECRET_KEY =
            "secretsdfghjkjhghjhghjhjkjhghjkjhgfghjhg21243e5wredwedywe5te4343tewqawsertyusdfghjkerftgyhujdfgxdewefcvhj"
    }

    fun createJWTToken(user: User): String {
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .claim("email", user.email)
            .claim("username", user.name)
            .claim("isAdmin", user.isAdmin)
            .setExpiration(Date(Date().time + 24 * 60 * 60 * 1000))
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact()
    }
}