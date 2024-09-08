package com.hibob.functional_programming
import jakarta.inject.Singleton
import java.math.BigDecimal

@Singleton
class BankAccountHandler {
    /*
        This method goes over all bank accounts.
        If the balance is positive, it considers it a good account; if negative, it's bad; and if zero, it's neutral.
        For those with no bad analysis, it promotes their type and returns the account with the new type and its analysis.
     */
    fun handleAccounts(bankAccounts: List<BankAccount>): List<BankAccountWithAnalysis> {
        return giveAnalysis(bankAccounts)
    }

    private fun giveAnalysis(bankAccounts: List<BankAccount>): List<BankAccountWithAnalysis> {
        return bankAccounts.map { account ->
            when {
                account.balance > BigDecimal.ZERO -> {
                    val newBalance = account.balance * BigDecimal("1.01")
                    val updatedCustomerType = upgrade(account)
                    BankAccountWithAnalysis(
                        account.copy(balance = newBalance, customerType = updatedCustomerType),
                        GoodAnalysis
                    )
                }
                account.balance < BigDecimal.ZERO -> {
                    val updatedCustomerType = downgrade(account)
                    BankAccountWithAnalysis(
                        account.copy(customerType = updatedCustomerType),
                        BadAnalysis
                    )
                }
                else -> BankAccountWithAnalysis(account, NoAnalysis)
            }
        }
    }

    private fun upgrade(bankAccount: BankAccount): CustomerType = when (bankAccount.customerType) {
            is Small -> Medium
            is Medium -> Large
            is Large -> VIP
            is VIP -> VIP
    }

    private fun downgrade(bankAccount: BankAccount): CustomerType = when (bankAccount.customerType) {
            is Small -> Small
            is Medium -> Small
            is Large -> Medium
            is VIP -> Large
    }
}
