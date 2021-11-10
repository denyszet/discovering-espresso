package com.example.todoapp.test.essentials.lesson_03_hamcrest_matchers

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.todoapp.test.essentials.BaseTest
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Test

/**
 * Demonstrates [org.hamcrest] General purpose matchers.
 */
class GeneralPurposeHamcrestMatchersTest : BaseTest() {

    /**
     * [CoreMatchers.is] decorates is(equalTo(x)) Matcher, retaining its behaviour.
     */
    @Test
    fun generalPurposeMatcherIs() {
        onView(withClassName(`is`(FloatingActionButton::class.java.canonicalName)))
    }

    /**
     * [CoreMatchers.equalTo] matches when the examined object is logically equal to
     * the specified operand.
     */
    @Test
    fun generalPurposeMatcherEqualTo() {
        onView(withClassName(equalTo(FloatingActionButton::class.java.canonicalName)))
    }

    /**
     * [CoreMatchers.not] inverts the wrapped matcher logic.
     */
    @Test
    fun generalPurposeMatcherNot() {
        onView(not(isChecked()))
    }

    /**
     * [CoreMatchers.instanceOf] matches and object of a specific type.
     */
    @Test
    fun generalPurposeMatcherInstanceOf() {
        onView(instanceOf(EditText::class.java))
    }
}
