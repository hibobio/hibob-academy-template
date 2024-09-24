package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@BobDbTest
class EmployeeDaoTest @Autowired constructor(private val sql: DSLContext) {
    private val employeeDao = EmployeeDao(sql)
    private val companyId1 = 1L

    @Test
    fun `should throw exception when employee does not exist`() {
        val nonExistentEmployee = EmployeeIn(
            firstName = "",
            lastName = "",
            companyId = companyId1
        )

        val exception = assertThrows<RuntimeException> {
            employeeDao.getEmployee(nonExistentEmployee)
        }
        assertEquals("Failed to fetch employee", exception.message)
    }

    @Test
    fun `should return employee when exists`() {
        val employee = EmployeeIn(
            firstName = "Rachel",
            lastName = "Green",
            companyId = 1L,
        )

        val returnEmployee = employeeDao.getEmployee(employee)

        val expectedEmployee = EmployeeOut(returnEmployee.id, employee.firstName, employee.lastName, returnEmployee.role, employee.companyId )

        assertEquals(expectedEmployee, returnEmployee)
    }
}