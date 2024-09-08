package com.hibob.academy.types

import com.hibob.types.*
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class StoreService {

    fun pay(cart: List<Cart>, payment: Payment): Map<String, Check> {
        return cart.associate {
            it.clientId to checkout(it, payment)
        }
    }

    private fun fail(message: String): Nothing {
        throw IllegalStateException(message)
    }

    private fun customTrueFilter(cart: Cart): List<Product> {
        return cart.products.filter {product ->
            product.custom as? Boolean ?: false
        }
    }

    private fun checkout(cart: Cart, payment: Payment) : Check {
        val products = customTrueFilter(cart)
        Statuses.FAILURE
        val total = products.sumOf { product -> product.price }

        return checkPayment(cart, payment, total)
    }

    private fun checkPayment(cart: Cart, payment: Payment, total: Double) : Check {
        val status = when (payment) {
            is Payment.Cash -> fail("Payment cannot be cash")
            is Payment.CreditCard -> isCreditCardSupported(payment, total)
            is Payment.PayPal -> isPayPalSupported(payment)
        }
        if (status)
            return Check(cart.clientId, Statuses.SUCCESS, total)
        return Check(cart.clientId, Statuses.FAILURE, 0.0)
    }

    private fun isCreditCardSupported(credit: Payment.CreditCard, total: Double) : Boolean {
        return credit.number.length == 10 &&
               credit.expiryDate.isAfter(LocalDate.now()) &&
               credit.limit >= total &&
               isCreditTypeSupported(credit)
    }

    private fun isCreditTypeSupported(credit: Payment.CreditCard): Boolean {
        if (credit.type == CreditCardType.VISA ||
            credit.type == CreditCardType.MASTERCARD)
            return true
        else
            return false
    }

    private fun isPayPalSupported(payment: Payment.PayPal) : Boolean {
        return payment.email.contains('@')
    }
}

enum class Statuses {
    SUCCESS, FAILURE
}


