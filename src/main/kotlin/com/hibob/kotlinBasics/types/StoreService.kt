package com.hibob.academy.types

import com.hibob.kotlinBasics.types.*
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class StoreService {

    fun pay(cart: List<Cart>, payment: Payment): Map<String, Check> {
       return cart.map({it -> it.clientId to checkout(it, payment)}).toMap()

    }

    fun checkout(cart: Cart, payment: Payment):Check {
        val total = cart.products.filter { isCustomTrue(it) }.sumOf { it.price }

        if(!validatePaymentMethod(payment, total)){
            return Check(cart.clientId, Statuses.FAILURE, 0.0)
        }
        return Check(cart.clientId, Statuses.SUCCESS, total)
    }

    fun validatePaymentMethod(payment: Payment, total: Double): Boolean {
        return when (payment) {
            is Payment.Cash -> fail("invalid payment method")
            is Payment.PayPal -> payment.email.contains("@")
            is Payment.CreditCard -> validateCard(payment, total)
        }
    }

    fun validateCard(card : Payment.CreditCard, total: Double): Boolean {
        return (card.type == CreditCardType.VISA || card.type == CreditCardType.MASTERCARD) && card.limit > total &&
                card.number.length > 9 && card.expiryDate.isAfter(LocalDate.now())
    }

    fun fail(errorMsg: String): Nothing {
        throw IllegalStateException(errorMsg)
    }

    fun isCustomTrue(product: Product): Boolean {
        return (product.custom as? Boolean) ?: false
    }
}