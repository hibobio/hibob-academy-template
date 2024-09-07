package com.hibob.academy.types

import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class StoreService {

    fun pay(cart: List<Cart>, payment: Payment): Map<String, Check> {
        val result = mutableMapOf<String, Check>()
         for (cart in cart) {
             val check = checkout(cart, payment)

             result[cart.clientId] = check
         }
       return result
    }
}







