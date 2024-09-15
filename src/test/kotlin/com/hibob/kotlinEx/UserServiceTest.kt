package com.hibob.kotlinEx
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.kotlin.whenever

class UserServiceTest {

    private lateinit var userDao: UserDao
    private lateinit var notificationService: NotificationService
    private lateinit var emailVerificationService: EmailVerificationService
    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        userDao = mock(UserDao::class.java)
        notificationService = mock(NotificationService::class.java)
        emailVerificationService = mock(EmailVerificationService::class.java)
        userService = UserService(userDao, notificationService, emailVerificationService)
    }

    @Test
    fun `registerUser should throw exception if user already exists`() {
        // Arrange
        val user = User(1L, "John Doe", "john@example.com", "password123")
        whenever(userDao.findById(1L)).thenReturn(user) // User already exists

        // Act & Assert
        val exception = assertThrows(IllegalArgumentException::class.java) {
            userService.registerUser(user)
        }
        assertEquals("User already exists", exception.message)
    }

    @Test
    fun `verifyUserEmail should throw exception if verification fails`() {
        // Arrange
        val user = User(1L, "John Doe", "john@example.com", "password123", isEmailVerified = false)
        whenever(userDao.findById(1L)).thenReturn(user) // User exists
        whenever(emailVerificationService.verifyEmail(user.email, "token")).thenReturn(false) // Verification fails

        // Act & Assert
        val exception = assertThrows(IllegalArgumentException::class.java) {
            userService.verifyUserEmail(1L, "token")
        }
        assertEquals("Email verification failed", exception.message)
    }
}