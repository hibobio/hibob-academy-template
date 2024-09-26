package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.dao.OwnerTable
import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@BobDbTest
class EmployeeDaoTest @Autowired constructor(private val sql: DSLContext) {
    private val dao = EmployeeDao(sql)
    private val employee = EmployeeTable.instance
    private val companyId: Long = -1

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(employee).where(employee.companyId.eq(companyId)).execute()
    }

    @Test
    fun `getEmployee should be successful`() {
        val newEmployee = NewEmployee("Hi", "Bob", Role.HR, companyId, Department.UX)
        dao.insertEmployee(newEmployee)
        val employee = Employee(newEmployee.firstName, newEmployee.lastName, newEmployee.companyId)
        val employeeDao = dao.getEmployee(employee)
        assertNotNull(employeeDao)
        assertEquals(employeeDao?.role, newEmployee.role)
        assertEquals(employeeDao?.companyId, newEmployee.companyId)
    }
}