package com.hibob.kotlinBasic

open class Meeting(
    val name: String,
    val locasetion: Location
) {
    val participants = mutableListOf<Participant>()

    fun addParticipant(participant: Participant) {
        participants.add(participant)
    }
}

class PersonalReview(
    name: String,
    val participant: Participant,
    val reviewers: List<Participant>,
    location: Location
) : Meeting(name, location) {

    init {
        println("Personal Review created successfully!")
    }
}

// Abstract base class for different location types
sealed class Location {
    abstract val street: String
    abstract val city: String
    abstract val county: String
}

// Location class for US
data class USLocation(
    override val street: String,
    override val city: String,
    override val county: String,
    val zipCode: String
) : Location()

// Location class for UK
data class UKLocation(
    override val street: String,
    override val city: String,
    override val county: String,
    val postcode: String
) : Location()