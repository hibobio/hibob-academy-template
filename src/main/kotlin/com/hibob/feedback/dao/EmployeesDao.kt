package com.hibob.feedback.dao

import com.hibob.academy.utils.JooqTable
import jakarta.ws.rs.BadRequestException
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class EmployeesDao(private val sql: DSLContext) {

    class EmployeesTable(tablename: String) : JooqTable(tablename) {
        val employeeId = createUUIDField("id")
        val firstName = createVarcharField("first_name")
        val lastName = createVarcharField("last_name")
        val role = createVarcharField("role")
        val department = createVarcharField("department")
        val companyId = createUUIDField("company_id")

        companion object {
            val instance = EmployeesTable("employees")
        }
    }

    private val employeesTable = EmployeesTable.instance

    fun createEmployee(firstName: String, lastName: String, role: String, companyId: UUID, department: String): UUID{
        return sql.insertInto(employeesTable)
            .set(employeesTable.firstName, firstName)
            .set(employeesTable.lastName, lastName)
            .set(employeesTable.role, role)
            .set(employeesTable.companyId, companyId)
            .set(employeesTable.department, department)
            .returning(employeesTable.employeeId)
            .fetchOne()!![employeesTable.employeeId]
    }

    fun getDepartmentById(employeeId: UUID, companyId: UUID): String { //MIGHT DELETE LATER
        return sql.select(employeesTable.department)
            .from(employeesTable)
            .where(employeesTable.employeeId.eq(employeeId))
            .and(employeesTable.companyId.eq(companyId))
            .fetchOne()?.let { it[employeesTable.department] }
            ?: throw BadRequestException("No employee with that id")
    }

    fun getRoleById(employeeId: UUID, companyId: UUID): String {
        return sql.select(employeesTable.role)
            .from(employeesTable)
            .where(employeesTable.employeeId.eq(employeeId))
            .and(employeesTable.companyId.eq(companyId))
            .fetchOne()?.let { it[employeesTable.role] }
            ?: throw BadRequestException("No employee with that id")
    }
}
