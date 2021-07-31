package com.example.todoapp.test.essentials.lesson_05_view_assertions

import android.widget.Spinner
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.todoapp.test.essentials.BaseTest
import org.junit.Test

class ViewAssertionsTest : BaseTest() {

    /**
     * View matcher that matches a [Spinner] based on selected item.
     */
    @Test
    fun doesNotExistAssertion() {
        onView(withText("")).check(doesNotExist())
    }

    /**
     * View matcher that matches a [Spinner] based on selected item.
     */
    @Test
    fun matchesAssertion() {
        onView(withText("")).check(matches(isDisplayed()))
    }
}
