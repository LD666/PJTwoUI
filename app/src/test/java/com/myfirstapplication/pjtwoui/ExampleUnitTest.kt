package com.myfirstapplication.pjtwoui

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun check() {
        assertNotEquals(1, 2 + 2)
    }

    @Test
    fun check_one() {
        assertNotEquals("hallo", "ha?", "hallo")
    }

    @Test
    fun check_array() {
        var array_one = arrayOf(1, 2, 3, 4, 5)
        var array_two = arrayOf(1, 2, 5, 4, 5)
        assertArrayEquals(array_one, array_two)
    }

}
