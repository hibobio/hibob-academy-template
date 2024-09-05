package com.hibob.academy.types

import com.hibob.kotlinBasics.types.*
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class StoreService {

    fun pay(cart: List<Cart>, payment: Payment): Map<String, Check> {
        return cart.associate { it.clientId to checkout(it, payment) }
    }

    private fun checkout(cart: Cart, payment: Payment): Check {
        val total = cart.products.filter { isCustomTrue(it) }.sumOf { it.price }

        if (!isValidPaymentMethod(payment, total)) {
            return Check(cart.clientId, Statuses.FAILURE, 0.0)
        }
        return Check(cart.clientId, Statuses.SUCCESS, total)
    }

    private fun isValidPaymentMethod(payment: Payment, total: Double): Boolean {
        return when (payment) {
            is Payment.Cash -> fail("invalid payment method")
            is Payment.PayPal -> payment.email.contains("@")
            is Payment.CreditCard -> isValidateCreditCard(payment, total)
        }
    }

    private fun isValidateCreditCard(card: Payment.CreditCard, total: Double): Boolean {
        return isSupportedType(card.type) && isNotOverLimit(limit = card.limit, total = total) && isCardDetailsValid(
            card
        )
    }

    private fun isSupportedType(cardType: CreditCardType): Boolean =
        (cardType == CreditCardType.VISA || cardType == CreditCardType.MASTERCARD)

    private fun isCardDetailsValid(card: Payment.CreditCard): Boolean =
        card.expiryDate.isAfter(LocalDate.now()) && card.number.length == 10

    private fun isNotOverLimit(limit: Double, total: Double): Boolean = limit > total

    private fun fail(errorMsg: String): Nothing {
        throw IllegalStateException(errorMsg)
    }

    private fun isCustomTrue(product: Product): Boolean {
        return (product.custom as? Boolean) ?: false
    }
}