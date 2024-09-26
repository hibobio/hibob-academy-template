//package com.hibob.academy.service
//
//import com.hibob.academy.dao.Example
//import org.hamcrest.MatcherAssert.assertThat
//import org.hamcrest.Matchers
//import com.hibob.academy.dao.ExampleDao
//import org.junit.jupiter.api.Test
//import org.mockito.kotlin.any
//import org.mockito.kotlin.mock
//import org.mockito.kotlin.whenever
//import kotlin.random.Random
//
//class ExampleServiceTest {
//    private val exampleDaoMock = mock<ExampleDao>{}
//    private val exampleService = ExampleService(exampleDaoMock)
//
//    @Test
//    fun `test example service`() {
//        val example = Example(Random.nextLong(), Random.nextLong(), "a")
//        whenever(exampleDaoMock.readExample(any())).thenReturn(example)
//        assertThat(exampleService.get(1), Matchers.equalTo(example))
//    }
//
//}