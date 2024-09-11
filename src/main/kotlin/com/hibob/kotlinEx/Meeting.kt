package com.hibob.kotlinEx

open class Meeting (val name: String, val location: Location, val participants: MutableList<Participant>){

    open fun addParticipant(participant: Participant){
        participants.add(participant)
    }

}

class PersonalMeeting(
    name: String,
    location: Location,
    participant: Participant, val reviewers: MutableList<Participant>
) : Meeting(name, location, mutableListOf(participant)) {

    init {
        println("Personal Review created successfully.")
    }

    // Optionally, override addParticipant to enforce the single participant rule
    override fun addParticipant(participant: Participant) {
        println("Cannot add more participants to a Personal Meeting.")
    }

    fun addReviewer(reviewer: Participant){
        reviewers.add(reviewer)
    }
}