//1. create class called Store that initlize by day and list of products
//2. add property to that indicate if the store is open the store is open all the day expect saturday
//3. add property to that indicate number of product
//4. add val that get receipts //no need to implement the method but its an heavy task
//5. I want to count number of calling get receipts
//6. write vairable that initilize only when calling create method
package com.hibob.kotlinBascis

//import Product
import java.time.DayOfWeek
import java.time.LocalDate

class Store(val day: LocalDate, val products: List<Product>) {
    val isOpen: Boolean
        get() = day.dayOfWeek != DayOfWeek.SATURDAY

    val numProducts: Int
        get() = products.size

    val receipts: String
        get() {
            countReceipts++
            return "Test"
        }

    var countReceipts: Int = 0

    lateinit var myLazy: String
}