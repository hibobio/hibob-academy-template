package com.hibob.academy.employeeFeedback.service

import com.hibob.academy.employeeFeedback.dao.Employee
import com.hibob.academy.employeeFeedback.dao.EmployeeDao
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.ws.rs.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class SessionEmployeeService @Autowired constructor(private val employeeDao: EmployeeDao) {
    companion object {
        const val SECRET_KEY =
            "secretsdfghjkjhghjhghjhjkjhghjkjhgfghjhg21243e5wredwedywe5te4343tewqawsertyusdfghjkerftgyhujdfgxdewefcvhj"
    }

    fun createJWTToken(employee: Employee): String {
        val employeeDetails = employeeDao.getEmployee(employee) ?: throw NotFoundException("Employee not found")

        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .claim("id", employeeDetails.id)
            .claim("companyId", employeeDetails.companyId)
            .claim("role", employeeDetails.role)
            .claim("department", employeeDetails.department)
            .setExpiration(Date(Date().time + 24 * 60 * 60 * 1000))
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact()
    }
}