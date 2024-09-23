/*package com.hibob.feedback.resource

import com.hibob.feedback.dao.Feedback
import com.hibob.feedback.dao.FeedbackInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import java.net.http.HttpHeaders
/*
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FeedbackResourceTest{
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun createFeedback(){
        val feedbackInput = FeedbackInput("hey all good", true)
        val headers = org.springframework.http.HttpHeaders()
        headers.set("Content-Type", "application/json")
        val request = HttpEntity(feedbackInput, headers)

        val response = restTemplate.postForEntity("/feedback/v1/create",request, Feedback::class.java)
        println("Response body: ${response.body}")

        assertEquals(HttpStatus.CREATED, response.statusCode)
    }
}*/