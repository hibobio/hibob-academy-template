package com.hibob.kotlinEx

abstract class Location (val street:String, val city:String, val county:String)


class ukLocation(street:String, city:String, county:String, val postcode:String) : Location(street, city, county)

class usLocation(street:String, city:String, county:String, val zipcode:String) : Location(street, city, county)