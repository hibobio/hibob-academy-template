package com.hibob.bootcamp.unittests

data class User(val id: Long, val name: String, val email: String, val password: String, val isEmailVerified: Boolean = false)

interface UserDao {
    fun findById(userId: Long): User?
    fun save(user: User): Boolean
    fun update(user: User): Boolean
}

interface NotificationService {
    fun sendEmail(email: String, message: String): Boolean
}

interface EmailVerificationService {
    fun sendVerificationEmail(email: String): Boolean
    fun verifyEmail(email: String, token: String): Boolean
}

class UserService(
    private val userDao: UserDao,
    private val notificationService: NotificationService,
    private val emailVerificationService: EmailVerificationService
) {
    fun registerUser(user: User): Boolean {
        if (userDao.findById(user.id) != null) {
            throw IllegalArgumentException("User already exists")
        }

        val isSaved = userDao.save(user.copy(isEmailVerified = false))
        if (!isSaved) {
            throw IllegalStateException("User registration failed")
        }

        val isVerificationEmailSent = emailVerificationService.sendVerificationEmail(user.email)
        if (!isVerificationEmailSent) {
            throw IllegalStateException("Failed to send verification email")
        }

        return true
    }

    fun verifyUserEmail(userId: Long, token: String): Boolean {
        val user = userDao.findById(userId) ?: throw IllegalArgumentException("User not found")

        val isEmailVerified = emailVerificationService.verifyEmail(user.email, token)
        if (!isEmailVerified) {
            throw IllegalArgumentException("Email verification failed")
        }

        val updatedUser = user.copy(isEmailVerified = true)
        val isUpdated = userDao.update(updatedUser)

        if (isUpdated) {
            notificationService.sendEmail(user.email, "Welcome ${user.name}!")
        }

        return isUpdated
    }
}
