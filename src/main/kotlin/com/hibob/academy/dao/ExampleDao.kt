//package com.hibob.academy.dao
//
//import com.hibob.academy.utils.JooqTable
//import com.hibob.academy.utils.asIs
//import org.jooq.DSLContext
//import org.springframework.stereotype.Component
//import com.hibob.academy.utils.from
//
//@Component
//class ExampleDao(private val sql: DSLContext) {
//
//    private val table = ExampleTable.instance
//
//    fun createExample(companyId: Long, data: String) {
//        sql.insertInto(table).columns(table.companyId, table.data)
//            .values(companyId, data).execute()
//    }
//
//    fun readExample(companyId: Long): Example? =
//        sql.selectFrom(table)
//            .where(table.companyId.eq(companyId))
//            .fetch(exampleMapper()).first()
//
//    fun exampleMapper() = ::Example.from(
//        table.id.asIs(),
//        table.companyId.asIs(),
//        table.data.asIs(),
//    )
//}
//
//class ExampleTable(tableName: String) : JooqTable(tableName) {
//    val id = createBigIntField("id")
//    val companyId = createBigIntField("company_id")
//    val data = createVarcharField("data")
//
//    companion object {
//        val instance = ExampleTable("example")
//    }
//}
