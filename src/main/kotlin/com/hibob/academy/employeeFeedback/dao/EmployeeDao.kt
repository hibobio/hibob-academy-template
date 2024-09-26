package com.hibob.academy.employeeFeedback.dao

import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper
import org.springframework.stereotype.Component

@Component
class EmployeeDao(private val sql: DSLContext) {
    private val employeeTable = EmployeeTable.instance

    private val employeeMapper = RecordMapper<Record, EmployeeData> { record ->
        EmployeeData(
            record[employeeTable.id],
            Role.fromDatabaseValue(record[employeeTable.role]),
            record[employeeTable.companyId],
            Department.fromDatabaseValue(record[employeeTable.department])
        )
    }

    fun getEmployee(employee: Employee): EmployeeData? {
        return sql.select()
        .from(employeeTable)
        .where(employeeTable.firstName.eq(employee.firstName))
        .and(employeeTable.lastName.eq(employee.lastName))
        .and(employeeTable.companyId.eq(employee.companyId))
        .fetchOne(employeeMapper)
    }

    fun insertEmployee(newEmployee: NewEmployee): Int {
        return sql.insertInto(employeeTable)
            .set(employeeTable.firstName, newEmployee.firstName)
            .set(employeeTable.lastName, newEmployee.lastName)
            .set(employeeTable.role, newEmployee.role.name)
            .set(employeeTable.companyId, newEmployee.companyId)
            .set(employeeTable.department, newEmployee.department.name)
            .execute()
    }

}