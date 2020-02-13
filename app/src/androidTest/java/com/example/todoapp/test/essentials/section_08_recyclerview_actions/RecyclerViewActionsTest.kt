package com.example.todoapp.test.essentials.section_08_recyclerview_actions

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R.id.*
import com.example.todoapp.test.essentials.BaseTest
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test

/**
 * Demonstrates Espresso [RecyclerView] actions usage in Kotlin.
 */
class RecyclerViewActionsTest : BaseTest() {

    private val item2 = "item 2"

    @Test
    fun addNewToDosChained() {
        12.generateToDos()
        onView(withId(tasks_list))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(10, scrollTo()))
                .perform(scrollToPosition<RecyclerView.ViewHolder>(1))
                .perform(scrollToPosition<RecyclerView.ViewHolder>(11))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(11, click()))
        Espresso.pressBack()
        onView(withId(tasks_list))
                // Position 1 but to-do item is the second in a list. Starting from zero.
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(task_detail_title)).check(matches(withText(item2)))
    }

    @Test
    fun completeToDo() {
        2.generateToDos()

        // Complete the to-do by clicking on checkbox.
        onView(allOf(withId(todo_complete), hasSibling(withText(item2)))).perform(click())
        onView(allOf(withId(todo_title), withText(item2))).perform(click())
        onView(withId(task_detail_title)).check(matches(withText(item2)))
    }

    private fun Int.generateToDos() {
        for (i in 1..this) {
            val toDoTitle = "item $i"
            val toDoDescription = "description $i"
            onView(withId(fab_add_task)).perform(click())
            onView(withId(add_task_title))
                    .perform(typeText(toDoTitle), closeSoftKeyboard())
            onView(withId(add_task_description))
                    .perform(typeText(toDoDescription), closeSoftKeyboard())
            onView(withId(fab_edit_task_done)).perform(click())
        }
    }
}
