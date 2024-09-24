package com.hibob.feedback.dao

import com.hibob.academy.utils.BobDbTest
import jakarta.ws.rs.BadRequestException
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@BobDbTest
class EmployeesDaoTest @Autowired constructor(private val sql: DSLContext) {
    private val employeesDao = EmployeesDao(sql)
    private val employeesTable = EmployeesDao.EmployeesTable.instance
    private val companyId = UUID.randomUUID()

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(employeesTable).where(employeesTable.companyId.eq(companyId)).execute()
    }

    @Test
    fun `create new employee`() {
        val employeeId = employeesDao.createEmployee("Hi", "Bob", "admin", companyId, "HR")
        assertTrue(employeeId is UUID)
    }

    @Test
    fun `get employee department`() {
        val employeeId = employeesDao.createEmployee("Hi", "Bob", "admin", companyId, "HR")
        assertEquals("HR", employeesDao.getDepartmentById(employeeId, companyId))
    }

    @Test
    fun `get employee role`() {
        val employeeId = employeesDao.createEmployee("Hi", "Bob", "admin", companyId, "HR")
        assertEquals("admin", employeesDao.getRoleById(employeeId, companyId))
    }

    @Test
    fun `throwing exception when no employee exists - department`() {
        assertEquals(
            "No employee with that id",
            org.junit.jupiter.api.assertThrows<BadRequestException> {
                employeesDao.getDepartmentById(
                    UUID.randomUUID(),
                    companyId
                )
            }.message
        )
    }

    @Test
    fun `throwing exception when no employee exists - role`() {
        assertEquals(
            "No employee with that id",
            org.junit.jupiter.api.assertThrows<BadRequestException> {
                employeesDao.getRoleById(
                    UUID.randomUUID(),
                    companyId
                )
            }.message
        )
    }
}