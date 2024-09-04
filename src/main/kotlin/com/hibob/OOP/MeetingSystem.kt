package com.hibob.OOP

data class Meeting(
    val name: String, val location: Location
)

data class Participant(
    val name: String, val email: String
)

//class PersonalReview : Meeting {
////    constructor(
////        name: String, participant: Participant
////        ) : super(name)
//}

fun addParticipant(participants: MutableList<Participant>, participant: Participant) {
    participants.add(participant)
}

fun main() {
    val p1 = Participant("Adi", "adifi436@gmail.com")
    val uk = UKLocation("Westminster", "London", "UK", "1111")
    val us = USLocation("Abby Road", "New York", "US", "222")
    val m1 = Meeting("Academy", uk)

    println(p1)
    println(m1)
    println(uk)
    println(us)
}


