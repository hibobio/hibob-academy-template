package com.hibob.kotlinEx

interface Location {
    val street: String
    val city: String
    val county: String
}

data class ukLocation(
    override val street: String,
    override val city: String,
    override val county: String,
    val postcode: String
) : Location

data class usLocation(
    override val street: String,
    override val city: String,
    override val county: String,
    val zipcode: String
) : Location
