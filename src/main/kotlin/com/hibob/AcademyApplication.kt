package com.hibob

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
	exclude = [
		FlywayAutoConfiguration::class,
		UserDetailsServiceAutoConfiguration::class,
		SecurityAutoConfiguration::class,
	]
)
class AcademyApplication

fun main(args: Array<String>) {
	runApplication<AcademyApplication>(*args)
}