package com.hibob.kotlinBasic

/**
 * Exercise Instructions:
 *
 * Write tests for addPerson method:
 *
 * Test adding a unique person.
 * Test adding a duplicate person and ensure it throws the expected exception.
 * Test adding multiple people, checking that the list grows appropriately.
 *
 *
 * Write tests for removePerson method:
 *
 * Test removing a person that exists in the list.
 * Test trying to remove a person that does not exist, ensuring it returns false.
 * Test the state of the list after multiple add and remove operations.
 *
 *
 * Write tests for getPeopleSortedByAgeAndName method:
 *
 * Test with an empty list.
 * Test with one person.
 * Test with multiple people to ensure they are sorted first by age, then by name.
 * Test with edge cases like people with the same name but different ages and vice versa.
 *
 */
data class Person(
    val name: String,
    val age: Int
)

data class PeopleStatistics(
    val averageAge: Double,
    val youngest: Person,
    val oldest: Person,
    val ageCount: Map<Int, Int>
)

class ListManager {
    private val people: MutableList<Person> = mutableListOf()

    fun addPerson(person: Person): Boolean {
        if (people.any { it.name == person.name && it.age == person.age }) {
            throw IllegalArgumentException("Duplicate person cannot be added.")
        }
        return people.add(person)
    }

    fun removePerson(person: Person): Boolean {
        return people.remove(person)
    }

    fun getPeopleSortedByAgeAndName(): List<Person> {
        return people.sortedWith(compareBy<Person> { it.age }.thenBy { it.name })
    }

    fun sumOfPeople(): Int {
        return people.size
    }

    fun containsPeople(person: Person): Boolean {
        return people.contains(person)
    }
    fun calculateStatistics(): PeopleStatistics? {
        if (people.isEmpty()) {
            return null
        }
        val averageAge = people.map { it.age }.average()
        val youngest = people.minByOrNull { it.age }
        val oldest = people.maxByOrNull { it.age }
        val ageCount = people.groupingBy { it.age }.eachCount()

        return youngest?.let { young ->
            oldest?.let { old ->
                PeopleStatistics(averageAge, young, old, ageCount)
            }
        }
    }
}