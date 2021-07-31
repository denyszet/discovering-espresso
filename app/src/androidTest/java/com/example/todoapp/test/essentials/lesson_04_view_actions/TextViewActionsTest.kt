package com.example.todoapp.test.essentials.lesson_04_view_actions

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.todoapp.test.essentials.BaseTest
import org.junit.Test

/**
 * Demonstrates Espresso text [ViewActions] usage.
 */
class TextViewActionsTest : BaseTest() {

    @Test
    fun textViewActionsTypeText() {
        onView(withId(R.id.fab_add_task)).perform(click())

        onView(withId(R.id.add_task_title))
                .perform(typeText("item 1"), closeSoftKeyboard())

        onView(withId(R.id.add_task_description))
                .perform(typeText("description 1"), closeSoftKeyboard())

        onView(withId(R.id.fab_edit_task_done)).perform(click())
    }

    @Test
    fun textViewActionsClearText() {
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(typeText("item 1"), closeSoftKeyboard())
        onView(withId(R.id.add_task_description))
                .perform(typeText("description 1"), closeSoftKeyboard())
        onView(withId(R.id.fab_edit_task_done)).perform(click())
        onView(withText("item 1")).perform(click())
        onView(withId(R.id.fab_edit_task)).perform(click())

        onView(withId(R.id.add_task_title)).perform(clearText())
    }

    @Test
    fun textViewActionsTypeTextIntoFocusedView() {
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(typeText("item 1"), closeSoftKeyboard())
        onView(withId(R.id.add_task_description))
                .perform(typeText("description 1"), closeSoftKeyboard())
        onView(withId(R.id.fab_edit_task_done)).perform(click())
        onView(withText("item 1")).perform(click())
        onView(withId(R.id.fab_edit_task)).perform(click())

        onView(withId(R.id.add_task_title))
                .perform(clearText(), typeTextIntoFocusedView("edited item 1"))
    }

    @Test
    fun textViewActionsReplaceText() {
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(typeText("item 1"), closeSoftKeyboard())
        onView(withId(R.id.add_task_description))
                .perform(typeText("description 1"), closeSoftKeyboard())
        onView(withId(R.id.fab_edit_task_done)).perform(click())
        onView(withText("item 1")).perform(click())
        onView(withId(R.id.fab_edit_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(clearText(), typeTextIntoFocusedView("edited item 1"))

        onView(withId(R.id.add_task_description)).perform(replaceText("edited description 1"))
        onView(withId(R.id.fab_edit_task_done)).perform(click())
    }
}
