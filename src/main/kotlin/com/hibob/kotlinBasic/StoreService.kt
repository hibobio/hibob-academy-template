package com.hibob.academy.types

import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class StoreService {

    fun pay(cart: List<Cart>, payment: Payment): Map<String, Check> {
       return mapOf()
    }
}

fun isValidProduct(custom: Any?): Boolean {
    return when (custom) {
        is Boolean -> custom
        else -> false
    }
}

fun fail(message: String): Nothing {
    throw IllegalStateException(message)

}

private fun processCreditCard(creditCard: Payment.CreditCard, total: Double): Boolean {
    if (creditCard.cradNumber.length < 10) return false

    if (creditCard.expiryDate.isBefore(LocalDate.now())) return false

    if(creditCard.type != CreditCardType.VISA && creditCard.type != CreditCardType.MASTERCARD) return false

    if(creditCard.limit < total) return false

    return true
}

private fun processPayPal(payPal: Payment.PayPal): Boolean {
    return payPal.email.contains("@")
}

fun checkout(cart: Cart, payment: Payment): Check {

    val validProduct = cart.products.filter { isValidProduct(it.custom) }

    val total = validProduct.sumOf { it.productPrice }

    val status = when (payment) {
        is Payment.CreditCard -> processCreditCard(payment, total)
        is Payment.PayPal -> processPayPal(payment)
        is Payment.Cash -> fail("Cash payment is not allowed")
        else -> false
    }

    val checkStatuses = if (status) Statuses.SUCCESS else Statuses.FAILURE

    val finalTotal = if (status) total else 0.0

    cart.clientId to Check(cart.clientId, checkStatuses, finalTotal)
}
