import com.hibob.bootcamp.BankAccount
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Write unit tests to verify that the deposit and withdraw methods function correctly.
 * Handle edge cases, such as invalid inputs (e.g., negative amounts).
 * Ensure that the getBalance method returns the correct balance after a series of deposits and withdrawals.
 */

class BankAccountTest {

    @Test
    fun `deposit valid amount increases balance`() {
        val initialBalance = 100.0
        val depositAmount = 50.0
        val expectedBalance = initialBalance + depositAmount
        val bankAccount = BankAccount(initialBalance)

        val newBalance = bankAccount.deposit(depositAmount)
        assertEquals(expectedBalance, newBalance, "Balance after deposit should be increased by the deposit amount")
    }

    @Test
    fun `deposit negative or zero amount throws IllegalArgumentException`() {
        val initialBalance = 0.0
        val bankAccount = BankAccount(initialBalance)
        val depositAmount = -50.0
        assertThrows<IllegalArgumentException> { bankAccount.deposit(depositAmount) }
    }

    @Test
    fun `withdraw valid amount decreases balance`() {
        val initialBalance = 100.0
        val bankAccount = BankAccount(initialBalance)
        val amount = 50.0
        val expectedBalance = initialBalance - amount
        assertEquals(expectedBalance, bankAccount.withdraw(amount))
    }

    @Test
    fun `withdraw amount greater than balance throws IllegalArgumentException`() {
        val initialBalance = 100.0
        val bankAccount = BankAccount(initialBalance)
        val amount = 500.0
        assertThrows<IllegalArgumentException> { bankAccount.withdraw(amount) }
    }

    @Test
    fun `withdraw negative or zero amount throws IllegalArgumentException`() {
        val initialBalance = 0.0
        val bankAccount = BankAccount(initialBalance)
        val amount = -50.0
        assertThrows<IllegalArgumentException> { bankAccount.withdraw(amount) }
    }

    @Test
    fun `getBalance returns the correct balance`() {
        val bankAccount = BankAccount(200.0)
        val balance = bankAccount.getBalance()
        assertEquals(200.0, balance)
    }
}
