package com.hibob.kotlinBasics.types

import java.time.DayOfWeek

//1. create class called Store that initlize by day and list of products
//2. add property to that indicate if the store is open the store is open all the day expect saturday
//3. add property to that indicate number of product
//4. add val that get receipts //no need to implement the method but its an heavy task
//5. I want to count number of calling get receipts
//6. write vairable that initilize only when calling create method

//1
class Store(val dayOfWeek: DayOfWeek, val products: List<Product>) {
    //2
    var isOpen: Boolean = dayOfWeek != DayOfWeek.SATURDAY
    //3
    val numberOfProducts: Int = products.size
    //4-6
    val recipet: List<Boolean>
        get(){
            recipetCallingCounter++
            return listOf(true)
        }
    var recipetCallingCounter = 0

    //6
    lateinit var lazyVar: Any
    fun create(valueToLazyVal: Any){
        lazyVar = valueToLazyVal
    }
}