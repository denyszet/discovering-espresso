package com.example.todoapp.test.essentials.lesson_03_hamcrest_matchers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.todoapp.test.essentials.BaseTest
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.junit.Test

/**
 * Demonstrates [org.hamcrest] matchers that combine multiple matchers.
 */
class CombiningHamcrestMatchersTest : BaseTest() {

    /**
     * [CoreMatchers.allOf] matches an object when all of the specified matchers match.
     */
    @Test
    fun objectMatcherAllOf() {
        onView(
            allOf(
                withId(R.id.todo_title),
                withText("item 1")
            )
        )
    }

    /**
     * [CoreMatchers.anyOf] matches an object when any of the specified matchers match.
     */
    @Test
    fun objectMatcherAnyOf() {
        onView(
            anyOf(
                instanceOf(FloatingActionButton::class.java),
                withId(R.id.fab_edit_task_done)
            )
        )
    }
}
