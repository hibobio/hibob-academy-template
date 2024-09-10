package com.hibob.kotlinBasics

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ListManagerTest {
    @Test
    fun `adding a unique person`() {
        val manager = ListManager()
        manager.addPerson(Person("bob", 27))
        assertEquals(1, manager.getPeople().size)
    }

    @Test
    fun `adding a duplicate person and throws the expected exception`() {
        val manager = ListManager()
        manager.addPerson(Person("bob", 27))
        assertThrows(IllegalArgumentException::class.java) {
            manager.addPerson(Person("bob", 27))
        }
    }

    @Test
    fun `adding a unique person twice`() {
        val manager = ListManager()
        manager.addPerson(Person("bob", 27))
        manager.addPerson(Person("boby", 28))
        assertEquals(2, manager.getPeople().size)
    }

    @Test
    fun `adding a duplicate person twice`() {
        val manager = ListManager()
        manager.addPerson(Person("bob", 27))
        manager.removePerson(Person("bob", 27))
        assertEquals(0, manager.getPeople().size)
    }

    @Test
    fun `trying to remove a person that does not exist, returns false`() {
        val manager = ListManager()
        assertEquals(false, manager.removePerson(Person("bob", 27)))
    }

    @Test
    fun `the state of the list after multiple add and remove operations`() {
        val manager = ListManager()
        manager.addPerson(Person("bob", 27))
        manager.addPerson(Person("alon", 28))
        manager.addPerson(Person("check", 28))
        manager.removePerson(Person("alon", 28))
        assertEquals(listOf(Person("bob", 27), Person("check", 28)), manager.getPeople())
    }

    @Test
    fun `the get people sorted, return empty list`() {
        val manager = ListManager()
        assertEquals(emptyList<Person>(), manager.getPeople())
    }

    @Test
    fun `get people sorted returning one person list`() {
        val manager = ListManager()
        manager.addPerson(Person("bob", 27))
        assertEquals(listOf(Person("bob", 27)), manager.getPeople())
    }

    @Test
    fun `sorting by age and then by name`() {
        val manager = ListManager()
        manager.addPerson(Person("bob", 1))
        manager.addPerson(Person("bob", 2))
        manager.addPerson(Person("alon", 2))
        assertEquals(
            listOf(Person("bob", 1), Person("alon", 2), Person("bob", 2)),
            manager.getPeopleSortedByAgeAndName()
        )
    }

    @Test
    fun `adding pepole with same age, diffrent name`() {
        val manager = ListManager()
        manager.addPerson(Person("bob", 27))
        manager.addPerson(Person("bobi", 27))
        assertEquals(2, manager.getPeople().size)
    }

    @Test
    fun `adding pepole with different age, same name`() {
        val manager = ListManager()
        manager.addPerson(Person("bob", 27))
        manager.addPerson(Person("bob", 28))
        assertEquals(2, manager.getPeople().size)
    }

    @Test
    fun `not removing pepole with same name, diffrent age`() {
        val manager = ListManager()
        manager.addPerson(Person("bob", 27))
        assertEquals(false, manager.removePerson(Person("bob", 28)))
    }

    @Test
    fun `not removing pepole with different name, same age`() {
        val manager = ListManager()
        manager.addPerson(Person("bob", 27))
        assertEquals(false, manager.removePerson(Person("bobi", 27)))
    }
}