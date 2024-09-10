package com.hibob.kotlinBasic
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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
class ListManagerTest {

    @Test
    fun `Test adding a unique person`() {
        assertEquals(Person("chezi nikop", 27), Person("chezi nikop", 27))
    }

    @Test
    fun `adding a duplicate person and ensure it throws the expected exception`() {
        val listManager = ListManager()
        listManager.addPerson(person = Person("chezi nikop", 27))

        assertThrows<IllegalArgumentException> {
            listManager.addPerson(Person("chezi nikop", 27))
        }
    }

    @Test
    fun `Test adding multiple people, checking that the list grows appropriately`() {
        val listManager = ListManager()
        listManager.addPerson(person = Person("chezi nikop", 27))
        listManager.addPerson(person = Person("eli cohen", 27))

        assertEquals(2, listManager.sumOfPeople())
    }

    @Test
    fun `removing a person that exists in the list`() {
        val listManager = ListManager()
        listManager.addPerson(person = Person("chezi nikop", 27))

        assertEquals(true, listManager.removePerson(person = Person("chezi nikop", 27)))

    }

    @Test
    fun `trying to remove a person that does not exist, ensuring it returns false`() {
        assertEquals(false, ListManager().removePerson(person = Person("chezi nikop", 27)))
    }

    @Test
    fun `Test the state of the list after multiple add and remove operations`() {
        val listManager = ListManager()
        listManager.addPerson(person = Person("chezi nikop", 27))
        listManager.addPerson(person = Person("eli cohen", 27))
        listManager.removePerson(person = Person("chezi nikop", 27))

        assertTrue(listManager.containsPeople(Person("eli cohen", 27)))
        assertEquals(false, listManager.containsPeople(Person("chezi nikop", 27)))
    }

    @Test
    fun `tests for getPeopleSortedByAgeAndName with an empty list`() {
        val listManager = ListManager()
        val listManagerAfterSorting = listManager.getPeopleSortedByAgeAndName()

        assertEquals(0, listManagerAfterSorting.size)
    }

    @Test
    fun `tests for getPeopleSortedByAgeAndName method with one person`() {
        val listManager = ListManager()
        listManager.addPerson(person = Person("chezi nikop", 27))

        assertEquals(Person("chezi nikop", 27), listManager.getPeopleSortedByAgeAndName().first())
    }

    @Test
    fun `tests for getPeopleSortedByAgeAndName method with multiple people to ensure they are sorted first by age, then by name`() {
        val listManager = ListManager()
        listManager.addPerson(person = Person("chezi nikop", 27))
        listManager.addPerson(person = Person("avi nikop", 28))

        assertEquals(Person("chezi nikop", 27), listManager.getPeopleSortedByAgeAndName().first())
    }

    @Test
    fun `tests for getPeopleSortedByAgeAndName method with edge cases like people with the same name but different ages and vice versa`() {
        val listManager = ListManager()
        listManager.addPerson(person = Person("chezi nikop", 27))
        listManager.addPerson(person = Person("chezi nikop", 28))
        listManager.addPerson(person = Person("eli nikop", 27))

        assertEquals(Person("chezi nikop", 27), listManager.getPeopleSortedByAgeAndName()[0])
        assertEquals(Person("eli nikop", 27), listManager.getPeopleSortedByAgeAndName()[1])
        assertEquals(Person("chezi nikop", 28), listManager.getPeopleSortedByAgeAndName()[2])
    }

    @Test
    fun `tests for calculateStatistics with an empty list`() {
        val listManager = ListManager()
        val listManagerAfterSorting = listManager.calculateStatistics()

        assertEquals(null, listManagerAfterSorting)
    }

    @Test
    fun `test for calculateStatistics with multiple people to see averageAge`() {
        val listManager = ListManager()
        listManager.addPerson(person = Person("chezi nikop", 30))
        listManager.addPerson(person = Person("eli nikop", 40))

        assertEquals(35.0, listManager.calculateStatistics()?.averageAge)
    }

    @Test
    fun `test for calculateStatistics with multiple people to see youngest`() {
        val listManager = ListManager()
        listManager.addPerson(person = Person("chezi nikop", 30))
        listManager.addPerson(person = Person("eli nikop", 40))

        assertEquals(Person("chezi nikop", 30), listManager.calculateStatistics()?.youngest)
    }

    @Test
    fun `test for calculateStatistics with multiple people to see oldest`() {
        val listManager = ListManager()
        listManager.addPerson(person = Person("chezi nikop", 30))
        listManager.addPerson(person = Person("eli nikop", 40))

        assertEquals(Person("eli nikop", 40), listManager.calculateStatistics()?.oldest)
    }

    @Test
    fun `test for calculateStatistics with multiple people to see ageCount`() {
        val listManager = ListManager()
        listManager.addPerson(person = Person("chezi nikop", 30))
        listManager.addPerson(person = Person("mosh nikop", 30))
        listManager.addPerson(person = Person("eli nikop", 40))

        assertEquals(2, listManager.calculateStatistics()?.ageCount?.get(30))
        assertEquals(1, listManager.calculateStatistics()?.ageCount?.get(40))
    }
}




