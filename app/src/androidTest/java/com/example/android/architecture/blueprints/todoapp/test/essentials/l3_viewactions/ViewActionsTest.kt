package com.example.android.architecture.blueprints.todoapp.test.essentials.l3_viewactions

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R.id.*
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.helpers.TestData
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test

/**
 * Demonstrates ViewActions usage in Kotlin.
 */
class ViewActionsTest : BaseTest() {

    private var toDoTitle = ""
    private var toDoDescription = ""

    @Before
    fun setUp() {
        toDoTitle = TestData.getToDoTitle()
        toDoDescription = TestData.getToDoDescription()
    }

    @Test
    fun addsNewToDo() {
        // adding new TO-DO
        onView(withId(fab_add_task)).perform(click())
        onView(withId(add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard())
        onView(withId(add_task_description))
                .perform(typeText(toDoDescription), closeSoftKeyboard())
        onView(withId(fab_edit_task_done)).perform(click())
        // verifying new TO-DO with title is shown in the TO-DO list
        onView(withText(toDoTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun checksToDoStateChange() {
        // adding new TO-DO
        onView(withId(fab_add_task)).perform(click())
        onView(withId(add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard())
        onView(withId(add_task_description))
                .perform(typeText(toDoDescription), closeSoftKeyboard())
        onView(withId(fab_edit_task_done)).perform(click())

        // marking our TO-DO as completed
        onView(withId(todo_complete)).perform(click())

        // filtering out the completed TO-DO
        onView(withId(menu_filter)).perform(click())
        onView(allOf(withId(title), withText("Active"))).perform(click())
        onView(withId(todo_title)).check(matches(not(isDisplayed())))
        onView(withId(menu_filter)).perform(click())
        onView(allOf(withId(title), withText("Completed"))).perform(click())
        onView(withId(todo_title))
                .check(matches(allOf(withText(toDoTitle), isDisplayed())))
    }

    @Test
    fun editsToDo() {
        val editedToDoTitle = "Edited $toDoTitle"
        val editedToDoDescription = "Edited $toDoDescription"

        // add new TO-DO
        onView(withId(fab_add_task)).perform(click())
        onView(withId(add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard())
        onView(withId(add_task_description))
                .perform(typeText(toDoDescription), closeSoftKeyboard())
        onView(withId(fab_edit_task_done)).perform(click())

        // edit TO-DO
        onView(withText(toDoTitle)).perform(click())
        onView(withId(fab_edit_task)).perform(click())
        onView(withId(add_task_title))
                .perform(replaceText(editedToDoTitle), closeSoftKeyboard())
        onView(withId(add_task_description))
                .perform(replaceText(editedToDoDescription), closeSoftKeyboard())
        onView(withId(fab_edit_task_done)).perform(click())

        // verify edited TO-DO is shown
        onView(withText(editedToDoTitle)).check(matches(isDisplayed()))
    }
}
