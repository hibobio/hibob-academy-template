package com.hibob.academy.employeeFeedback.dao

import org.jooq.DSLContext
import org.jooq.RecordMapper
import org.jooq.Record

class EmployeeDao(private val sql: DSLContext) {
    private val employeeTable = EmployeeTable.instance

    private val employeeMapper = RecordMapper<Record, EmployeeOut>
    { record ->
        EmployeeOut(
            record[employeeTable.id],
            record[employeeTable.firstName],
            record[employeeTable.lastName],
            enumValueOf<RoleType>(record[employeeTable.rol]),
            record[employeeTable.companyId]
        )
    }

    fun getEmployee(employee: EmployeeIn) : EmployeeOut {
        return sql.select(employeeTable.id, employeeTable.firstName, employeeTable.lastName, employeeTable.rol, employeeTable.companyId)
            .from(employeeTable)
            .where(employeeTable.firstName.eq(employee.firstName))
            .and(employeeTable.lastName.eq(employee.lastName))
            .and(employeeTable.companyId.eq(employee.companyId))
            .fetchOne(employeeMapper) ?: throw RuntimeException("Failed to fetch employee")
    }

    fun deleteTable(companyId: Long) {
        sql.deleteFrom(employeeTable)
            .where(employeeTable.companyId.eq(companyId))
            .execute()
    }
}