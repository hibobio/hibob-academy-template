package com.hibob.kotlinEx

open class Meeting (val name: String, val location: Location, val participants: List<Participant>){

    open fun addParticipant(participant: Participant){
        val participants = participants.plus(participant)
    }

}

class PersonalMeeting(
    name: String,
    location: Location,
    participant: Participant, val reviewers: List<Participant>
) : Meeting(name, location, listOf(participant)) {

    init {
        println("Personal Review created successfully.")
    }

    // Optionally, override addParticipant to enforce the single participant rule
    override fun addParticipant(participant: Participant) {
        println("Cannot add more participants to a Personal Meeting.")
    }

    fun addReviewer(reviewer: Participant){
        val reviewers = reviewers.plus(reviewer)
    }
}