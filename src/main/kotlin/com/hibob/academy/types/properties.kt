package com.hibob.academy.types

import com.google.common.math.Stats
import com.hibob.types.Product
import java.time.DayOfWeek

//1. create class called Store that initlize by day and list of products
//2. add property to that indicate if the store is open all the day expect saturday
//3. add property to that indicate number of product
//4. add val that get receipts //no need to implement the method but its an heavy task
//5. I want to count number of calling get receipts
//6. write vairable that initilize only when calling create method

class Store(val day: DayOfWeek, val products: List<Product>) {
    val isOpen: Boolean
        get() = day != DayOfWeek.SATURDAY

    val numberOfProducts: Int
        get() = products.size

    var countCallingNumber: Int = 0
    val receipts: Int
        get() = countCallingNumber++


    lateinit var receiptsCalls: String

    fun onCreate() {
        println("Create receipts")
        receiptsCalls = "Receipts created"
    }
}