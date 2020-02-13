package com.example.todoapp.test.essentials.section_03_hamcrest_matchers

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.todoapp.test.essentials.BaseTest
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Test

/**
 * Demonstrates [org.hamcrest] General purpose matchers.
 */
class GeneralPurposeHamcrestMatchersTest : BaseTest() {

    /**
     * [CoreMatchers.is] decorates another Matcher, retaining its behaviour.
     */
    @Test
    fun generalPurposeMatcherIs() {
        onView(withClassName(`is`(FloatingActionButton::class.java.canonicalName)))
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
