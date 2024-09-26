package com.hibob.kotlinBasics

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ListManagerTest {



    @Test
    fun `add unique person`() {
        val listManager = ListManager()
        val person = Person("John", 30)
        val result = listManager.addPerson(person)
        assertTrue(result)
    }
    @Test
    fun `add duplicate person throws IllegalArgumentException`() {
        val listManager = ListManager()
        val person = Person("John", 30)
        listManager.addPerson(person)

        val exception = assertThrows(IllegalArgumentException::class.java) {
            listManager.addPerson(person)
        }
        assertEquals("Duplicate person cannot be added.", exception.message)
    }

    @Test
    fun `add multiple people increases list size`() {
        val listManager = ListManager()
        val person1 = Person("John", 30)
        val person2 = Person("Jane", 25)
        val person3 = Person("Doe", 40)

        listManager.addPerson(person1)
        listManager.addPerson(person2)
        listManager.addPerson(person3)
        assertEquals(3, listManager.getPeople().size)
    }

    @Test
    fun `remove person that exists in the list`(){
        val listManager = ListManager()
        val person1 = Person("John", 30)
        //val person2 = Person("Jane", 25)
        listManager.removePerson(person1)
        assertEquals(false, listManager.getPeopleSortedByAgeAndName().contains(person1))
    }


    @Test
    fun `remove person that does not exist returns false`() {
        val listManager = ListManager()
        val person1 = Person("John", 30)
        val result=listManager.removePerson(person1)
        assertFalse(result)
        assertEquals(false, listManager.getPeopleSortedByAgeAndName().contains(person1))
    }

    @Test
    fun `state of the list after multiple add and remove operations`() {
        val listManager = ListManager()
        val person1 = Person("John", 30)
        val person2 = Person("Jane", 25)
        val person3 = Person("Doe", 40)

        listManager.addPerson(person1)
        listManager.addPerson(person2)
        listManager.addPerson(person3)
        listManager.removePerson(person2)
        assertEquals(2, listManager.getPeople().size)
        assertTrue(listManager.getPeople().contains(person1))
        assertTrue(listManager.getPeople().contains((person3)))
        assertFalse(listManager.getPeople().contains(person2))
    }

    @Test
    fun `get people sorted by age and name with empty list`() {
        val listManager = ListManager()
        val sortedPeople = listManager.getPeopleSortedByAgeAndName()
        assertEquals(sortedPeople.size, listManager.getPeople().size)
    }


    @Test
    fun `get people sorted by age and name with one person`() {
        val listManager = ListManager()
        val person = Person("John", 30)
        listManager.addPerson(person)
        val sortedPeople = listManager.getPeopleSortedByAgeAndName()
        assertEquals(1, sortedPeople.size)
        assertEquals(person, sortedPeople[0])
    }


    @Test
    fun `get people sorted by age and name with multiple people`() {
        val listManager = ListManager()
        val person1 = Person("John", 30)
        val person2 = Person("Jane", 25)
        val person3 = Person("Doe", 40)
        listManager.addPerson(person1)
        listManager.addPerson(person2)
        listManager.addPerson(person3)
        val sortedPeople = listManager.getPeopleSortedByAgeAndName()
        assertEquals(listOf(person2, person1, person3), sortedPeople)
    }
    @Test
    fun `get people sorted by age and name with edge cases`() {
        val listManager = ListManager()
        val person1 = Person("John", 30)
        val person2 = Person("John", 25)
        val person3 = Person("Jane", 30)
        listManager.addPerson(person1)
        listManager.addPerson(person2)
        listManager.addPerson(person3)
        val sortedPeople = listManager.getPeopleSortedByAgeAndName()
        assertEquals(listOf(person2, person3, person1), sortedPeople)
    }

    @Test
    fun `calculate statistics for averageAge people`() {
        val listManager = ListManager()
        val person1 = Person("John", 30)
        val person2 = Person("John", 40)
        val person3 = Person("Jane", 20)
        listManager.addPerson(person1)
        listManager.addPerson(person2)
        listManager.addPerson(person3)

        assertEquals(30.0, listManager.calculateStatistics()?.averageAge)

    }


    @Test
    fun `calculate statistics with null`() {
        val listManager = ListManager()
        val result = listManager.calculateStatistics()
        assertNull(result)
    }
    @Test
    fun `calculate statistics with one person`() {
        val listManager = ListManager()
        val people = Person("Alice", 30)
        listManager.addPerson(people)
        val result = listManager.calculateStatistics()
        assertNotNull(result)
        assertEquals(30.0, result?.averageAge)
        assertEquals(listManager.getPeople()[0], result?.youngest)
        assertEquals(listManager.getPeople()[0], result?.oldest)
        assertEquals(mapOf(30 to 1), result?.ageCount)
    }


    @Test
    fun `calculate statistics with multiple people`() {
        val listManager = ListManager()
        val person1= Person("Alice", 30)
        val person2= Person("Bob", 25)
        val person3= Person("Charlie", 35)
        listManager.addPerson(person1)
        listManager.addPerson(person2)
        listManager.addPerson(person3)
        val result = listManager.calculateStatistics()
        assertNotNull(result)
        assertEquals(30.0, result?.averageAge)
        assertEquals(listManager.getPeople()[1], result?.youngest)
        assertEquals(listManager.getPeople()[2], result?.oldest)
        assertEquals(mapOf(25 to 1, 30 to 1, 35 to 1), result?.ageCount)
    }

    @Test
    fun `calculate statistics with duplicate ages`() {
        val listManager = ListManager()
        val person1 = Person("Alice", 30)
        val person2 = Person("Bob", 30)
        val person3 = Person("Charlie", 35)
        listManager.addPerson(person1)
        listManager.addPerson(person2)
        listManager.addPerson(person3)
        val result = listManager.calculateStatistics()

        assertNotNull(result)
        assertEquals(31.666666666666668, result?.averageAge)
        assertEquals(listManager.getPeople()[0], result?.youngest)
        assertEquals(listManager.getPeople()[2], result?.oldest)
        assertEquals(mapOf(30 to 2, 35 to 1), result?.ageCount)
    }
}