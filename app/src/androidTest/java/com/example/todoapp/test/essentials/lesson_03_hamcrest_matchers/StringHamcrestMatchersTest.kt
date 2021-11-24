package com.example.todoapp.test.essentials.lesson_03_hamcrest_matchers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.todoapp.test.essentials.BaseTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.startsWith
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.Test

/**
 * Demonstrates [org.hamcrest] String matchers.
 */
class StringHamcrestMatchersTest : BaseTest() {

    /**
     * [Matchers.isEmptyString] matches [String] when it has zero length.
     */
    @Test
    fun objectMatcherIsEmptyString() {
        onView(withText(`is`(emptyString())))
    }

    /**
     * [Matchers.isEmptyOrNullString] matches [String] when it is null or has zero length.
     */
    @Test
    fun objectMatcherEmptyOrNullString() {
        onView(withText(`is`(emptyOrNullString())))
    }

    /**
     * [CoreMatchers.startsWith] matches [String] when it starts with the specified [String].
     */
    @Test
    fun stringHamcrestMatchersStartsWith() {
        onView(withText(startsWith("item")))
    }

    /**
     * [Matchers.equalToIgnoringCase] matches [String] when it is equal to the specified
     * expectedString, ignoring case.
     */
    @Test
    fun stringHamcrestMatchersEqualToIgnoringCase() {
        onView(withText(equalToIgnoringCase("ITEM1")))
    }

    /**
     * [Matchers.equalToCompressingWhiteSpace] matches [String] when it is equal to the specified
     * expectedString, ignoring white spaces.
     */
    @Test
    fun stringHamcrestMatchersEqualToCompressingWhiteSpace() {
        onView(withText(equalToCompressingWhiteSpace("item     1")))
    }
}
