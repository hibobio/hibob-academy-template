package com.hibob.kotlinBasic

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import java.time.LocalDate

class StoreServiceTest {

    private val storeService = StoreService()

    private val tv = Product("1", "tv", 455.3, true)
    private val ps5 = Product("2", "ps5", 3325.0, true)
    private val car = Product("3", "car", 332522.0, false)
    private val cart1 = Cart("1", products = listOf(tv, ps5, car))
    private val cart2 = Cart("2", products = listOf(ps5))
    private val visa = Payment.CreditCard("3555654812", LocalDate.parse("2026-03-03"), CreditCardType.VISA, 5555555.5)
    private val visaIncorrectNumber =
        Payment.CreditCard("35556548", LocalDate.parse("2026-03-03"), CreditCardType.VISA, 5555555.5)
    private val amex =
        Payment.CreditCard("3555654812", LocalDate.parse("2026-03-03"), CreditCardType.AMERICAN_EXPRESS, 5555555.5)
    private val payPal = Payment.PayPal("test@com")
    private val invalidPayPal = Payment.PayPal("test")


    @Test
    fun `pay method should process successfully if the data valid`() {
        val res = storeService.pay(listOf(cart1, cart2), visa)

        assertThat(
            res, Matchers.equalTo(
                mapOf(
                    "1" to Check("1", Statuses.SUCCESS, 3780.3),
                    "2" to Check("2", Statuses.SUCCESS, 3325.0)
                )
            )
        )
    }

    @Test
    fun `pay method should process successfully in case pf using valid paypal`() {
        val res = storeService.pay(listOf(cart1, cart2), payPal)

        assertThat(
            res, Matchers.equalTo(
                mapOf(
                    "1" to Check("1", Statuses.SUCCESS, 3780.3),
                    "2" to Check("2", Statuses.SUCCESS, 3325.0)
                )
            )
        )
    }

    @Test
    fun `pay method should failed in case of using invalid paypal`() {
        val res = storeService.pay(listOf(cart1), invalidPayPal)

        assertThat(
            res, Matchers.equalTo(
                mapOf(
                    "1" to Check("1", Statuses.FAILURE, 0.0)
                )
            )
        )
    }

    @Test
    fun `pay method should failed in case of using cash`() {
        assertThrows(IllegalStateException::class.java) {
            storeService.pay(listOf(cart1), Payment.Cash)
        }
    }


    @Test
    fun `pay method should failed in case of incorrect visa`() {
        val res = storeService.pay(listOf(cart1, cart2), visaIncorrectNumber)

        assertThat(
            res, Matchers.equalTo(
                mapOf(
                    "1" to Check("1", Statuses.FAILURE, 0.0),
                    "2" to Check("2", Statuses.FAILURE, 0.0)
                )
            )
        )
    }

    @Test
    fun `pay method should failed in case of incorrect type`() {
        val res = storeService.pay(listOf(cart1, cart2), amex)

        assertThat(
            res, Matchers.equalTo(
                mapOf(
                    "1" to Check("1", Statuses.FAILURE, 0.0),
                    "2" to Check("2", Statuses.FAILURE, 0.0)
                )
            )
        )
    }
}