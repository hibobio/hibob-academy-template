package com.hibob.kotlinBasics

/**
 * Exercise Instructions:
 *
 * Write tests for addPerson method:adding a duplicate person and ensure it throws the expected exception
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
data class Person(val name: String, val age: Int)

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

    fun getPeople(): List<Person> {
        return people
    }

    fun getPeopleSortedByAgeAndName(): List<Person> {
        return people.sortedWith(compareBy<Person> { it.age }.thenBy { it.name })
    }

}
