package com.hibob.academy.service

import com.hibob.bootcamp.unittests.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UserServiceTest {
    private val userDao = mock<UserDao>{}
    private val notificationService = mock<NotificationService>()
    private val emailVerificationService = mock<EmailVerificationService>()
    private val userService = UserService(userDao, notificationService, emailVerificationService)

    @Test
    fun `registerUser should fail and throw exception wen the user already exists`() {
        val user = User(id = 1, name = "chezi", email = "chezi@gmail.com", password = "123", isEmailVerified = true)

        whenever(userDao.findById(user.id)).thenReturn(user)

        val exceptionReturned = assertThrows<IllegalArgumentException>{
            userService.registerUser(user)
        }
        assertEquals("User already exists", exceptionReturned.message)
    }

    @Test
    fun `if the user save failed then we will get an exception`() {
        val user = User(id = 1, name = "chezi", email = "chezi@gmail.com", password = "123", isEmailVerified = true)

        whenever(userDao.save(user.copy(isEmailVerified = false))).thenReturn(false)

        val exceptionReturned = assertThrows<IllegalStateException>{
            userService.registerUser(user)
        }

        assertEquals("User registration failed", exceptionReturned.message)
    }

    @Test
    fun `if saving the email failed then we will get an exception`() {
        val user = User(id = 1, name = "chezi", email = "chezi@gmail.com", password = "123", isEmailVerified = true)

        whenever(userDao.save(user.copy(isEmailVerified = false))).thenReturn(true)
        whenever(emailVerificationService.sendVerificationEmail(user.email)).thenReturn(false)

        val exceptionReturned = assertThrows<IllegalStateException>{
            userService.registerUser(user)
        }
        assertEquals("Failed to send verification email", exceptionReturned.message)
    }

    @Test
    fun `if the registration were successful, the function returns true`() {
        val user = User(id = 1, name = "chezi", email = "chezi@gmail.com", password = "123", isEmailVerified = true)

        whenever(userDao.save(user.copy(isEmailVerified = false))).thenReturn(true)
        whenever(emailVerificationService.sendVerificationEmail(user.email)).thenReturn(true)
        assertTrue(userService.registerUser(user))
    }

    @Test
    fun `if the user is not exist we will gen exception`() {
        val user = User(id = 1, name = "chezi", email = "chezi@gmail.com", password = "123", isEmailVerified = true)
        val token = "chezi_token"

        whenever(userDao.findById(user.id)).thenReturn(null)

        val exceptionReturned = assertThrows<IllegalArgumentException>{
            userService.verifyUserEmail(user.id, token)
        }

        assertEquals("User not found", exceptionReturned.message )
    }

    @Test
    fun `if the token does not match the user's email we will get an exception`() {
        val user = User(id = 1, name = "chezi", email = "chezi@gmail.com", password = "123", isEmailVerified = true)
        val token = "chezi_token"

        whenever(userDao.findById(user.id)).thenReturn(user)
        whenever(emailVerificationService.verifyEmail(user.email, token)).thenReturn(false)

        val exceptionReturned = assertThrows<IllegalArgumentException>{
            userService.verifyUserEmail(user.id, token)
        }

        assertEquals("Email verification failed", exceptionReturned.message)
    }

    @Test
    fun `If the user update was successful the function will return true`() {
        val user = User(id = 1, name = "chezi", email = "chezi@gmail.com", password = "123", isEmailVerified = true)
        val token = "chezi_token"

        whenever(userDao.findById(user.id)).thenReturn(user)
        whenever(emailVerificationService.verifyEmail(user.email, token)).thenReturn(true)
        whenever(userDao.update(user)).thenReturn(true)

        assertTrue(userService.verifyUserEmail(user.id ,token))
    }

    @Test
    fun `If the user wasn't update successful the function will return false`() {
        val user = User(id = 1, name = "chezi", email = "chezi@gmail.com", password = "123", isEmailVerified = true)
        val token = "chezi_token"

        whenever(userDao.findById(user.id)).thenReturn(user)
        whenever(emailVerificationService.verifyEmail(user.email, token)).thenReturn(true)
        whenever(userDao.update(user)).thenReturn(false)

        assertFalse(userService.verifyUserEmail(user.id ,token))
    }
}