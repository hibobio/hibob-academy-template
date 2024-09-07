package com.hibob.kotlinBasic



import org.jooq.impl.QOM
import java.io.ObjectInputFilter
import java.time.LocalDate


//1. create data class of Cart that include: client Id and list of Products
//2. product object contain id, name, price and custom - custom can be either int, string or any other type
//3. create sealed class of payment that can be use by the following classes:
//    CreditCard - contains number, expiryDate, type and limit (type can be VISA, MASTERCARD, DISCOVER and AMERICAN_EXPRESS)
//    PayPal - contain email
//    Cash - without args
//4. add fail function that get message an argument and throw IllegalStateException
//5. write function check if custom is true and only if its true its valid product.
//6. write function called checkout and get cart and payment that pay the money
//   * only custom with true are valid
//   * cash payment is not valid to pay so if the payment is cash use fail function
//   * in case of credit card need to validate the expiryDate is after the current date + limit is bigger than the total we need to pay and we allow to use only  VISA or MASTERCARD
//   * in case of payPal validate we hae @
///  * the return value of this function, should be a data class with client id, status (success or failed) and total called Check
// 7. implement pay method



data class Cart(
    val clientId: String,
    val products: List<Product>
)

data class Product(
    val productId: String,
    val productName: String,
    val productPrice: Double,
    val custom: Any?
)


sealed class Payment {
    data class CreditCard(
        val cradNumber: String,
        val expiryDate: LocalDate,
        val type: CreditCardType,
        val limit: Double
    ) : Payment()

    data class PayPal(
        val email: String
    ) : Payment()

    data object Cash: Payment()
}

fun isValidProduct(custom: Any?): Boolean {
    return when (custom) {
        is Boolean -> custom
        else -> false
    }
}

enum class CreditCardType{
    VISA,
    MASTERCARD,
    DISCOVER,
    AMERICAN_EXPRESS
}

fun fail(message: String): Nothing {
    throw IllegalStateException(message)

}

data class Check(
    val clientId: String,
    val status: Statuses,
    val total: Double,
)

enum class Statuses {
    SUCCESS,
    FAILURE,
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

    return Check(cart.clientId, checkStatuses, finalTotal)
}



