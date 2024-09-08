package com.hibob.functional_programming
import java.math.BigDecimal

data class BankAccount(
    val customerId: Long,
    val customerType: CustomerType,
    val balance: BigDecimal
)

data class BankAccountWithAnalysis(
    val bankAccount: BankAccount,
    val analysis: Analysis
)

sealed class CustomerType
data object Small : CustomerType()
data object Medium : CustomerType()
data object Large : CustomerType()
data object VIP : CustomerType()

sealed class Analysis
data object GoodAnalysis : Analysis()
data object BadAnalysis : Analysis()
data object NoAnalysis : Analysis()
