package com.hibob.kotlinEx

import org.jooq.True
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.*

class ListManagerTest {

    @Test
    fun `adding unique Person`() {
        val listManager = ListManager()
        val person = Person("Gilad", 27)
        val output = listManager.addPerson(person)
        assertEquals(true, output)
    }

    @Test
    fun `adding duplicate Person`() {
        val listManager = ListManager()
        val person = Person("Gilad", 27)
        listManager.addPerson(person)
        assertThrows<IllegalArgumentException> { listManager.addPerson(person) }
    }

    @Test
    fun `adding multiple persons`() {
        val listManager = ListManager()
        val person = Person("Gilad", 27)
        listManager.addPerson(person)
        val firstSize = listManager.getPeopleSortedByAgeAndName().size
        listManager.addPerson(Person("Bob", 11))
        assertEquals(1, listManager.getPeopleSortedByAgeAndName().size-firstSize)
    }

    @Test
    fun `remove existing Person`() {
        val listManager = ListManager()
        val person = Person("Gilad", 27)
        listManager.addPerson(person)
        val output = listManager.removePerson(person)
        assertEquals(true, output)
    }

    @Test
    fun `remove non-existing Person`() {
        val listManager = ListManager()
        val person = Person("Gilad", 27)
        val output = listManager.removePerson(person)
        assertEquals(false, output)
    }

    @Test
    fun `multiple removes and addings`() {
        val listManager = ListManager()
        val gilad = Person("Gilad", 27)
        val bob = Person("Bob", 11)
        val keren = Person("Keren", 26)
        listManager.addPerson(gilad)
        listManager.addPerson(bob)
        listManager.addPerson(keren)
        listManager.removePerson(gilad)
        listManager.removePerson(bob)
        assertEquals(1, listManager.getPeopleSortedByAgeAndName().size)
    }

    @Test
    fun `get empty PeopleSortedByAgeAndName`() {
        val listManager = ListManager()
        assertEquals(0, listManager.getPeopleSortedByAgeAndName().size)
    }

    @Test
    fun `get one person in the list PeopleSortedByAgeAndName`() {
        val listManager = ListManager()
        val person = Person("Gilad", 27)
        listManager.addPerson(person)
        assertEquals(1, listManager.getPeopleSortedByAgeAndName().size)
    }

    @Test
    fun `check order by age preferred then name`() {
        val listManager = ListManager()
        val first = Person("b", 27)
        val second = Person("a", 28)
        listManager.addPerson(first)
        listManager.addPerson(second)
        assertEquals(mutableListOf(first, second), listManager.getPeopleSortedByAgeAndName())
    }

    @Test
    fun `check order by name when age is equals`() {
        val listManager = ListManager()
        val first = Person("a", 27)
        val second = Person("b", 27)
        listManager.addPerson(first)
        listManager.addPerson(second)
        assertEquals(mutableListOf(first, second), listManager.getPeopleSortedByAgeAndName())
    }

    @Test
    fun `check order after multiple addings`() {
        val listManager = ListManager()
        val first = Person("b", 27)
        val second = Person("c", 27)
        val third = Person("a", 28)
        listManager.addPerson(first)
        listManager.addPerson(second)
        listManager.addPerson(third)
        assertEquals(mutableListOf(first, second, third), listManager.getPeopleSortedByAgeAndName())
    }
}