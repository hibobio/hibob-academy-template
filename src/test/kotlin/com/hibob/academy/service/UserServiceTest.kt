package com.hibob.academy.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UserServiceTest{
    private val userDao= mock<UserDao>{}
    private val notificationService= mock<NotificationService>{}
    private val emailVerificationService= mock<EmailVerificationService>{}

    private val userService=UserService(userDao,notificationService,emailVerificationService)

    @Test
    fun `registerUser should throw exception if user already exists`() {
        val user = User(2L,"matan","@","123")
        whenever(userDao.findById(user.id)).thenReturn(user)

        val exception = assertThrows<IllegalArgumentException> {
            userService.registerUser(user)
        }
        assertEquals("User already exists", exception.message)

        verify(userDao).findById(user.id)
        verify(userDao, never()).save(any())
        verify(emailVerificationService, never()).sendVerificationEmail(any())

    }


    @Test
    fun `registerUser should throw exception if user registration failed`() {
        val user = User(2L,"matan","@","123")
        whenever(userDao.findById(user.id)).thenReturn(null)
        whenever(userDao.save(user)).thenReturn(false)
        val exception = assertThrows<IllegalStateException> {
            userService.registerUser(user)
        }
        assertEquals("User registration failed", exception.message)

        verify(userDao).findById(user.id)
        verify(userDao).save(user)
        verify(emailVerificationService, never()).sendVerificationEmail(any())
    }

    @Test
    fun `registerUser should throw exception if failed to send verification email`() {
        val user = User(2L,"matan","@","123")
        whenever(userDao.findById(user.id)).thenReturn(null)
        whenever(userDao.save(user)).thenReturn(true)
        whenever(emailVerificationService.sendVerificationEmail(user.email)).thenReturn(false)
        val exception = assertThrows<IllegalStateException> {
            userService.registerUser(user)
        }
        assertEquals("Failed to send verification email", exception.message)
        verify(userDao).findById(user.id)
        verify(userDao).save(user)
        verify(emailVerificationService).sendVerificationEmail(user.email)
    }

    @Test
    fun `registerUser should return true`() {
        val user = User(2L,"matan","@","123")
        whenever(userDao.findById(user.id)).thenReturn(null)
        whenever(userDao.save(user)).thenReturn(true)
        whenever(emailVerificationService.sendVerificationEmail(user.email)).thenReturn(true)

        assertTrue(userService.registerUser(user))
        verify(userDao).findById(user.id)
        verify(userDao).save(user)
        verify(emailVerificationService).sendVerificationEmail(user.email)
    }


    @Test
    fun `verifyUserEmail throw exception if user not found`() {
        val user = User(2L,"matan","@","123")
        val userId=2L
        val token="bla"
        whenever(userDao.findById(userId)).thenReturn(null)
        val exception = assertThrows<IllegalArgumentException> {
            userService.verifyUserEmail(userId, token)
        }
        assertEquals("User not found", exception.message)

        verify(userDao).findById(userId)
        verify(emailVerificationService, never()).verifyEmail("@", token)
        verify(userDao, never()).update(user)
        verify(notificationService, never()).sendEmail("@","sd")
    }

    @Test
    fun `verifyUserEmail throw exception if email verification failed`() {
        val userId=2L
        val token="bla"
        val user = User(2L,"matan","@","123")
        whenever(userDao.findById(userId)).thenReturn(user)
        whenever(emailVerificationService.verifyEmail(user.email, token)).thenReturn(false)
        val exception = assertThrows<IllegalArgumentException> {
            userService.verifyUserEmail(userId, token)
        }
        assertEquals("Email verification failed", exception.message)
        verify(userDao).findById(userId)
        verify(emailVerificationService).verifyEmail("@", token)
        verify(userDao, never()).update(user)
        verify(notificationService, never()).sendEmail("@","sd")
    }


    @Test
    fun `verifyUserEmail should return true and userService verifyUserEmail send true`() {
        val userId=2L
        val token="bla"
        val user = User(2L,"matan","@","123")
        whenever(userDao.findById(userId)).thenReturn(user)
        whenever(emailVerificationService.verifyEmail(user.email, token)).thenReturn(true)
        whenever(userDao.update(user.copy(isEmailVerified = true))).thenReturn(true)
        val result = userService.verifyUserEmail(userId, token)

        assertTrue(result)
        verify(userDao).findById(userId)
        verify(emailVerificationService).verifyEmail("@", token)
        verify(userDao).update(user.copy(isEmailVerified = true))
        verify(notificationService).sendEmail(user.email,"Welcome ${user.name}!")

    }

    @Test
    fun `verifyUserEmail should return true and userService verifyUserEmail send false`() {
        val token = "token"
        val user = User(2L, "matan", "@", "123")
        whenever(userDao.findById(user.id)).thenReturn(user)
        whenever(emailVerificationService.verifyEmail(user.email, token)).thenReturn(true)
        whenever(userDao.update(user.copy(isEmailVerified = true))).thenReturn(false)

        val result = userService.verifyUserEmail(user.id, token)

        assertFalse(result)

        verify(userDao).findById(user.id)
        verify(emailVerificationService).verifyEmail("@", token)
        verify(userDao).update(user.copy(isEmailVerified = true))
        verify(notificationService, never()).sendEmail("@","sd")
    }


}