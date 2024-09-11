//package com.hibob.academy.resource
//
//import com.hibob.academy.dao.Example
//import com.hibob.academy.service.ExampleService
//import org.hamcrest.CoreMatchers.`is`
//import org.hamcrest.MatcherAssert.assertThat
//import org.hamcrest.Matchers
//import org.junit.jupiter.api.Test
//import org.mockito.kotlin.any
//import org.mockito.kotlin.whenever
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
//import org.springframework.boot.test.mock.mockito.MockBean
//import org.springframework.boot.test.web.client.TestRestTemplate
//import org.springframework.http.HttpStatus
//import kotlin.random.Random
//
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//internal class ExampleResourceTest {
//
//    @Autowired
//    lateinit var restTemplate: TestRestTemplate
//
//    @MockBean
//    lateinit var exampleService: ExampleService
//
//    @Test
//    fun test() {
//        val example = Example(Random.nextLong(), Random.nextLong(), "a")
//        whenever(exampleService.get(1)).thenReturn(example)
//        val response = restTemplate.getForEntity("/example/1", ExampleResponse::class.java)
//        assertThat(response.statusCode, Matchers.`is`(HttpStatus.OK))
//        assertThat(response.body, `is`(ExampleResponse(example)))
//    }
//}
