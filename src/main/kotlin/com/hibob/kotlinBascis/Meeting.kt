package com.hibob.kotlinBascis

open class Meeting(
    val name: String,
    val location: Location
) {
    private val participants = mutableListOf<Participant>()
    fun addParticipant(participant: Participant) {
        participants.add(participant)
    }
}

abstract class Location{
    abstract val street: String
    abstract val city: String
    abstract val county: String
}

class US(override val street: String, override val city: String, override val county: String ,val zipCode:Int, ):Location()

class UK(override val street: String, override val city: String, override val county: String ,val postcode:Int, ):Location()

class PersonalReview(
    name: String,
    location: Location,
    val participant: Participant,
    val reviewers: List<Participant>,
) : Meeting(name, location) {
    init {
        println("SUCCESS!")
    }
}


