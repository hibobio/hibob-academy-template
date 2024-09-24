package com.hibob.feedback.dao

import com.hibob.academy.utils.JooqTable
import jakarta.ws.rs.BadRequestException
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper
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

    private val employeeMapper = RecordMapper<Record, Employee> { record ->
        Employee(
            record[employeesTable.employeeId],
            record[employeesTable.companyId],
            Role.valueOf(record[employeesTable.role].uppercase()),
            Department.valueOf(record[employeesTable.department].uppercase())
        )
    }

    fun createEmployee(firstName: String, lastName: String, role: String, companyId: UUID, department: String): UUID {
        return sql.insertInto(employeesTable)
            .set(employeesTable.firstName, firstName)
            .set(employeesTable.lastName, lastName)
            .set(employeesTable.role, role)
            .set(employeesTable.companyId, companyId)
            .set(employeesTable.department, department)
            .returning(employeesTable.employeeId)
            .fetchOne()!![employeesTable.employeeId]
    }

    fun getEmployeeByActiveUser(activeUser: ActiveUser): Employee {
        return sql.select()
            .from(employeesTable)
            .where(employeesTable.employeeId.eq(activeUser.employeeId))
            .and(employeesTable.companyId.eq(activeUser.companyId))
            .fetchOne(employeeMapper)
            ?: throw BadRequestException("No employee with that id")
    }

}
