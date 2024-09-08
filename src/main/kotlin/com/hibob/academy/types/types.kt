package com.hibob.types
import com.hibob.academy.types.Statuses
import java.time.LocalDate

data class Check(val employeeId: String, val status: Statuses, val total: Double)

data class Cart(val clientId: String, val products: List<Product>)

data class Product(val id: String, val name: String, val price: Double, val custom: Any)

sealed class Payment {
    data class CreditCard(val number: String, val expiryDate: LocalDate, val type: CreditCardType, val limit: Double) : Payment()
    data class PayPal(val email: String) : Payment()
    data object Cash : Payment()
}

enum class CreditCardType {
    VISA, MASTERCARD, DISCOVER, AMERICAN_EXPRESS
}