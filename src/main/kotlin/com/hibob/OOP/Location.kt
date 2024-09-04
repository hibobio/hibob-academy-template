package com.hibob.OOP

abstract class Location(
    val street: String, val city: String, val country: String) {
    override fun toString(): String {
        return "$street, $city, $country"
    }
}

class USLocation(
    street: String, city: String, country: String, val zipcode: String
) : Location(street, city, country) {
    override fun toString(): String {
        return "${super.toString()}, Zipcode: $zipcode"
    }
}


class UKLocation(
    street: String, city: String, country: String, val postcode: String
) : Location(street, city, country) {
    override fun toString(): String {
        return "${super.toString()}, Zipcode: $postcode"
    }
}
