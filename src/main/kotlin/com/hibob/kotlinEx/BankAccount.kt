package com.hibob.bootcamp
class BankAccount(private var balance: Double) {

    fun deposit(amount: Double): Double {
        if (amount <= 0) {
            throw IllegalArgumentException("Deposit amount must be greater than zero")
        }
        balance += amount
        return balance
    }

    fun withdraw(amount: Double): Double {
        if (amount > balance) {
            throw IllegalArgumentException("Insufficient balance")
        }
        if (amount <= 0) {
            throw IllegalArgumentException("Withdraw amount must be greater than zero")
        }
        balance -= amount
        return balance
    }

    fun getBalance(): Double {
        return balance
    }
}
