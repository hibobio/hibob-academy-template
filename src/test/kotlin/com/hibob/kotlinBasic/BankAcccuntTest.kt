import com.hibob.bootcamp.BankAccount
import org.junit.jupiter.api.Assertions.*
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
        val account = BankAccount(0.0)
        assertEquals(50.0, account.deposit(50.0))
    }

   @Test
    fun `deposit negative or zero amount throws IllegalArgumentException`() {
       val account = BankAccount(0.0)
       assertThrows(IllegalArgumentException::class.java) {
           account.deposit(-5.0)
       }
    }

    @Test
    fun `withdraw valid amount decreases balance`() {
        assertEquals(50.0, BankAccount(100.0).withdraw(50.0))
    }

        @Test
        fun `withdraw amount greater than balance throws IllegalArgumentException`() {
            assertThrows<IllegalArgumentException> {
                BankAccount(100.0).withdraw(101.0)
            }
        }

           @Test
           fun `withdraw negative or zero amount throws IllegalArgumentException`() {
               assertThrows<IllegalArgumentException> {
                   BankAccount(100.0).withdraw(-5.0)
               }
           }

             @Test
             fun `getBalance returns the correct balance`() {
                 assertEquals(100.0, BankAccount(100.0).getBalance())
             }
}

