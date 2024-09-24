package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@BobDbTest
class EmployeeDaoTest @Autowired constructor(private val sql: DSLContext) {
    private val employeeDao = EmployeeDao(sql)
    private val companyId1 = 1L
    private val companyId2 = 2L

    @Test
    fun `should throw exception when employee does not exist`() {
        val nonExistentEmployee = EmployeeIn(
            firstName = "",
            lastName = "",
            role = RoleType.EMPLOYEE,
            companyId = companyId1,
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
            role = RoleType.ADMIN,
            companyId = companyId1,
        )

        val returnEmployee = employeeDao.getEmployee(employee)

        val expectedEmployee = EmployeeOut(returnEmployee.id, employee.firstName, employee.lastName, employee.role, employee.companyId )

        assertEquals(expectedEmployee, returnEmployee)
    }

    @BeforeEach
    @AfterEach
    fun cleanup() {
        employeeDao.deleteTable(companyId1)
        employeeDao.deleteTable(companyId2)
    }
}