package com.hibob.academy.service

import com.hibob.bootcamp.unittests.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class UserServiceTest {
    private val userDao = mock<UserDao> {}
    private val notificationService = mock<NotificationService>()
    private val emailVerificationService = mock<EmailVerificationService>()
    private val userService = UserService(userDao, notificationService, emailVerificationService)

    @Test
    fun `try to insert user who already exsits`() {
        val user = User(1, "bob", "bob@hibob.com", "123456", true)
        whenever(userDao.findById(1)).thenReturn(user)
        assertThrows<IllegalArgumentException> { userService.registerUser(user) }
    }

    @Test
    fun `test user is not saved in dao`() {
        val user = User(1, "bob", "bob@hibob.com", "123456", true)
        whenever(userDao.findById(1)).thenReturn(null)
        whenever(userDao.save(user.copy(isEmailVerified = false))).thenReturn(false)
        assertThrows<IllegalStateException> { userService.registerUser(user) }
    }

    @Test
    fun `failed to send verifcation email`() {
        val user = User(1, "bob", "bob@hibob.com", "123456", true)
        whenever(userDao.findById(1)).thenReturn(null)
        whenever(userDao.save(user.copy(isEmailVerified = false))).thenReturn(true)
        whenever(emailVerificationService.sendVerificationEmail(user.email)).thenReturn(false)
        assertThrows<IllegalStateException> { userService.registerUser(user) }
    }

    @Test
    fun `sucsess returning true`() {
        val user = User(1, "bob", "bob@hibob.com", "123456", true)
        whenever(userDao.findById(1)).thenReturn(null)
        whenever(userDao.save(user.copy(isEmailVerified = false))).thenReturn(true)
        whenever(emailVerificationService.sendVerificationEmail(user.email)).thenReturn(true)
        assertEquals(true, userService.registerUser(user))
    }

    @Test
    fun `failed to find user`() {
        val user = User(1, "bob", "bob@hibob.com", "123456", true)
        whenever(userDao.findById(1)).thenReturn(null)
        assertThrows<IllegalArgumentException> { userService.verifyUserEmail(user.id, "token") }
    }

    @Test
    fun `email verification failed`() {
        val user = User(1, "bob", "bob@hibob.com", "123456", true)
        whenever(userDao.findById(1)).thenReturn(user)
        whenever(emailVerificationService.verifyEmail(user.email, "tpken")).thenReturn(false)
        assertEquals( ,assertThrows<IllegalArgumentException> { userService.verifyUserEmail(1, "token") }
    }

    @Test
    fun `varifey that send email being used and return true`() {
        val user = User(1, "bob", "bob@hibob.com", "123456", true)
        whenever(userDao.findById(1)).thenReturn(user)
        whenever(emailVerificationService.verifyEmail(user.email, "token")).thenReturn(true)
        whenever(userDao.update(user.copy(isEmailVerified = true))).thenReturn(true)
        whenever(notificationService.sendEmail(user.email, "Welcome ${user.name}!")).thenReturn(true)
        assertEquals(true, userService.verifyUserEmail(1, "token"))
        verify(notificationService).sendEmail(user.email, "Welcome ${user.name}!")
    }

    @Test
    fun `assert not sending email and false`() {
        val user = User(1, "bob", "bob@hibob.com", "123456", true)
        whenever(userDao.findById(1)).thenReturn(user)
        whenever(emailVerificationService.verifyEmail(user.email, "token")).thenReturn(true)
        whenever(userDao.update(user.copy(isEmailVerified = true))).thenReturn(false)
        whenever(notificationService.sendEmail(user.email, "Welcome ${user.name}!")).thenReturn(true)
        assertEquals(false, userService.verifyUserEmail(1, "token"))
        verify(notificationService, never()).sendEmail(user.email, "Welcome ${user.name}!")
    }
}