package com.hibob.academy.types

//1. create class called Store that initlize by day and list of products
//2. add property to that indicate if the store is open the store is open all the day expect saturday
//3. add property to that indicate number of product
//4. add val that get receipts //no need to implement the method but its an heavy task
//5. I want to count number of calling get receipts
// === (lazy, num of access, so I have to change property to var, ... func, use get() without lazy)
//6. write vairable that initilize only when calling create method (lateinit var type int)

import Product
import java.time.DayOfWeek
import java.time.LocalDate

data object Recipients

class Store (products: List<Product>, val day: LocalDate) {
    var isOpen : Boolean = when(LocalDate.now().dayOfWeek){
        DayOfWeek.SATURDAY -> false
        else -> true
    }
    val numOfProducts : Int = products.size

    private var accessCount: Int = 0
    val recipients: Recipients by lazy {
        accessCount++
        Recipients
    }
    lateinit var someInt: String
}
