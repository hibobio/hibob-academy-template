package com.hibob.kotlinBasic

import java.time.DayOfWeek

class Store(private val day: DayOfWeek, private val products: List<Product>) {
    val isOpen: Boolean
        get() = day != DayOfWeek.SATURDAY

    val numberOfProducts: Int
        get() = products.size

    var numberOfReceipts: Int = 0
        private set

    val getReceipts: List<String>
        get() {
            numberOfReceipts++
            return creatorReceipt()
        }

    private fun creatorReceipt(): List<String> {
        return listOf()
    }
}


