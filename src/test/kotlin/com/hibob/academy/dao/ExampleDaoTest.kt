//package com.hibob.academy.dao
//
//import com.hibob.academy.utils.BobDbTest
//import org.hamcrest.MatcherAssert.assertThat
//import org.hamcrest.Matchers
//import org.jooq.DSLContext
//import org.junit.jupiter.api.AfterEach
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import kotlin.random.Random
//
//@BobDbTest
//class ExampleDaoTest @Autowired constructor(private val sql: DSLContext)  {
//
//    private val dao = ExampleDao(sql)
//    val companyId = Random.nextLong()
//    val table = ExampleTable.instance
//
//    @BeforeEach
//    fun createTable() {
//        sql.createTable(table).columns(*table.fields()).execute()
//    }
//
//    @AfterEach
//    fun dropTable() {
//        sql.dropTable(table).execute()
//    }
//
//    @Test
//    fun createAndRead() {
//        dao.createExample(companyId, "Hello, Kotlin!")
//        dao.createExample(companyId, "Hello, micro-service!")
//        val actual = dao.readExample(companyId)
//        assertThat(actual, Matchers.equalTo(Example(actual!!.id, companyId, "Hello, Kotlin!")))
//    }
//}