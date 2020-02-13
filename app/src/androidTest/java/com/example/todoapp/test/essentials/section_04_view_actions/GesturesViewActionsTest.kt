package com.example.todoapp.test.essentials.section_04_view_actions

import android.widget.ImageButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.todoapp.test.essentials.BaseTest
import com.example.todoapp.test.essentials.utils.ToDoItem
import org.hamcrest.CoreMatchers.*
import org.junit.Test

/**
 * Demonstrates Espresso gestures [ViewActions] usage.
 */
class GesturesViewActionsTest : BaseTest() {

    @Test
    fun gesturesViewActionsScrollTo() {
        val toDoTitle = ToDoItem.title
        val toDoDescription = ToDoItem.description

        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard())
        onView(withId(R.id.add_task_description))
                .perform(typeText(toDoDescription), closeSoftKeyboard())

        onView(withId(R.id.makePhoto)).perform(scrollTo(), click())

        onView(withId(R.id.picture)).perform(click())
        onView(withId(R.id.fab_edit_task_done)).perform(click())
    }

    @Test
    fun gesturesViewActionsSwipeRightLeft() {
        onView(allOf(instanceOf(ImageButton::class.java), withParent(withId(R.id.toolbar))))
        onView(allOf(withId(R.id.design_menu_item_text), withText(R.string.settings_title))).perform(click())
        onView(withText(R.string.pref_header_notifications)).perform(click())

        onView(withId(android.R.id.switch_widget)).perform(swipeRight()).check(matches(isChecked()))
        onView(withId(android.R.id.switch_widget)).perform(swipeLeft()).check(matches(not(isChecked())))
    }

    @Test
    fun gesturesViewActionsSwipeUpDown() {
        10.generateToDos()

        onView(withId(R.id.tasksContainer)).perform(swipeDown())
        onView(withId(R.id.tasksContainer)).perform(swipeUp())
    }

    private fun Int.generateToDos() {
        for (i in 1..this) {
            val toDoTitle = "item $i"
            val toDoDescription = "description $i"
            onView(withId(R.id.fab_add_task)).perform(click())
            onView(withId(R.id.add_task_title))
                    .perform(typeText(toDoTitle), closeSoftKeyboard())
            onView(withId(R.id.add_task_description))
                    .perform(typeText(toDoDescription), closeSoftKeyboard())
            onView(withId(R.id.fab_edit_task_done)).perform(click())
        }
    }
}
