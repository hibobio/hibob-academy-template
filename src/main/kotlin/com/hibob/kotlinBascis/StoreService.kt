package com.hibob.kotlinBascis

import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class StoreService {

    fun pay(cart: List<Cart>, payment: Payment): Map<String, Check> {
        return cart.associate { myCart ->
            myCart.clientId to checkout(myCart, payment)
        }
    }
}


fun checkCardType(type: CreditCardType): Boolean {
    return when (type) {
        CreditCardType.MASTERCARD -> true
        CreditCardType.VISA -> true
        CreditCardType.DISCOVER -> false
        CreditCardType.AMERICAN_EXPRESS -> false
    }
}

fun checkPayment(payment: Payment, total: Double): Statuses {
    return when (payment) {
        is Payment.CreditCard -> creditCardTest(payment, total)
        is Payment.PayPal -> payPalStatuses(payment)
        is Payment.Cash -> fail("It is not allowed to pay in cash")
    }
}

fun checkout(cart: Cart, payment: Payment): Check {
    val total = cart.products
        .filter { product -> checkCustom(product.custom) }
        .map { product -> product.price }
        .reduce { acc, price -> acc + price }

    val status = checkPayment(payment, total)
    if (status == Statuses.FAILURE) {
        return Check(cart.clientId, status, 0.0)
    }
    return Check(cart.clientId, status, total)
}

fun fail(message: String): Nothing {
    throw IllegalStateException(message)
}

fun checkCustom(custom: Any): Boolean {
    return if (custom is Boolean) {
        custom
    } else false
}

fun creditCardTest(payment: Payment.CreditCard, total: Double): Statuses {
    return if (isValidatePayment(payment, total)) {
        Statuses.SUCCESS
    } else Statuses.FAILURE
}


fun isValidatePayment(payment: Payment.CreditCard, total: Double): Boolean {
    return (isPaymentExpiryDate(payment)
            && isPaymentWithinLimit(payment, total)
            && isPaymentNumberValid(payment)
            && isCardTypeValid(payment))
}

fun isPaymentExpiryDate(payment: Payment.CreditCard): Boolean {
    return payment.expiryDate > LocalDate.now()
}

fun isPaymentWithinLimit(payment: Payment.CreditCard, total: Double): Boolean {
    return payment.limit > total
}

fun isPaymentNumberValid(payment: Payment.CreditCard): Boolean {
    return payment.number.length == 10
}

fun isCardTypeValid(payment: Payment.CreditCard): Boolean {
    return checkCardType(payment.type)
}

fun payPalStatuses(payment: Payment.PayPal): Statuses {
    return if (payment.email.contains('@')) {
        Statuses.SUCCESS
    } else Statuses.FAILURE
}
