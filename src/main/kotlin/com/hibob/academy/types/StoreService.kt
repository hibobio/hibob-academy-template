package com.hibob.academy.types

import org.springframework.stereotype.Component
import java.time.LocalDate
import Cart
import Payment
import Check
import Statuses

const val ZERO_TOTAL_COSTS_ON_FAILED_BUY = 0.0
const val VISA_LEGIT_NUM_LENGTH = 10

fun fail(message: String) : Nothing?{
    throw IllegalStateException(message)
}

@Component
class StoreService {

    fun pay(cart: List<Cart>, payment: Payment): Map<String, Check> {
        val result = mutableMapOf<String, Check>()

        for (c in cart) {
            val check = checkout(c, payment)

            result[c.clientId] = check
        }
        return result
    }

    private fun isCustomTrue(custom: Any): Boolean {

        return when(custom) {
            is Boolean -> custom
            else -> false
        }
    }

    private fun checkout(cart: Cart, payment: Payment): Check {
        var totalPricesOfLegitProducts = 0.0

        cart.products.forEach { product ->
            if (isCustomTrue(product.custom)) {
                totalPricesOfLegitProducts += product.price
            }
        }

        val status: Statuses = when(payment) {
            is Payment.Cash -> {
                fail("payment is Cash")
                Statuses.FAILURE
                return Check(cart.clientId, Statuses.FAILURE, ZERO_TOTAL_COSTS_ON_FAILED_BUY)
            }

            is Payment.CreditCard -> {
                if (payment.expiryDate < LocalDate.now()) {
                    // fail("expiry date is in the past")
                    // update status to fail
                    Statuses.FAILURE
                } else if (payment.limit < totalPricesOfLegitProducts) {
                    Statuses.FAILURE
                } else if (payment.type !in listOf(CreditCardType.VISA, CreditCardType.MASTERCARD)) {
                    Statuses.FAILURE
                } else if (payment.number.length != VISA_LEGIT_NUM_LENGTH) {
                    Statuses.FAILURE
                } else { Statuses.SUCCESS }
            }

            is Payment.PayPal -> {
                if (!payment.email.contains('@')) {
                    Statuses.FAILURE
                } else {
                    Statuses.SUCCESS
                }
            }
            else -> Statuses.SUCCESS
        }
        return if (status == Statuses.SUCCESS) Check(cart.clientId, status, totalPricesOfLegitProducts) else Check(cart.clientId, status, ZERO_TOTAL_COSTS_ON_FAILED_BUY)
    }
}
