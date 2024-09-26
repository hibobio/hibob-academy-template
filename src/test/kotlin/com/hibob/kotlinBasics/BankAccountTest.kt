package com.hibob.kotlinBasics

import org.junit.jupiter.api.Assertions.*


import org.junit.jupiter.api.Test

/**
 * Write unit tests to verify that the deposit and withdraw methods function correctly.
 * Handle edge cases, such as invalid inputs (e.g., negative amounts).
 * Ensure that the getBalance method returns the correct balance after a series of deposits and withdrawals.
 */

class BankAccountTest {

    @Test
    fun `deposit valid amount increases balance`() {
        val bankAccount = BankAccount(20.0)
        assertEquals(50.0 , bankAccount.deposit(30.0))
    }

    @Test
    fun `deposit negative or zero amount throws IllegalArgumentException`() {
        val bankAccount = BankAccount(0.0)
        assertThrows(IllegalArgumentException::class.java) {
            bankAccount.deposit(0.0)
        }

        assertThrows(IllegalArgumentException::class.java) {
            bankAccount.deposit(-10.0)
        }
    }

    @Test
    fun `withdraw valid amount decreases balance`() {
        val bankAccount = BankAccount(50.0)
        assertEquals(20.0 , bankAccount.withdraw(30.0))
    }

    @Test
    fun `withdraw amount greater than balance throws IllegalArgumentException`() {
        val bankAccount = BankAccount(50.0)
        assertThrows(IllegalArgumentException::class.java) {
            bankAccount.withdraw(60.0)
        }
    }

    @Test
    fun `withdraw negative or zero amount throws IllegalArgumentException`() {
        val bankAccount = BankAccount(50.0)
        assertThrows(IllegalArgumentException::class.java) {
            bankAccount.withdraw(0.0)
        }

        assertThrows(IllegalArgumentException::class.java) {
            bankAccount.withdraw(-10.0)
        }
    }

    @Test
    fun `getBalance returns the correct balance`() {
        val bankAccount = BankAccount(50.0)
        assertEquals(50.0 , bankAccount.getBalance())
    }
}
