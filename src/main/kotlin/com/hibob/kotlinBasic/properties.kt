package com.hibob.kotlinBasic

class Store(private val day: String, private val products: List<Product>) {
    val isOpen: Boolean
        get() = day.lowercase() != "saturday"

    val numberOfProducts: Int
        get() = products.size

    var numberOfReceipts: Int = 0
        private set

    val getReceipts: List<String> by lazy {
        numberOfReceipts++
        creatorReceip()
    }

    private  fun creatorReceip(): List<String> {
        return listOf()
    }
}


