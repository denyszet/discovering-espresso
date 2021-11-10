@file:Suppress("DEPRECATION")

package com.example.todoapp.test.essentials.lesson_07_data_interaction

import android.preference.PreferenceActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.PreferenceMatchers.withKey
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.todoapp.test.essentials.BaseTest
import com.example.todoapp.test.essentials.utils.CommonElements.openDrawer
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Test
import androidx.test.espresso.DataInteraction

/**
 * Demonstrates [DataInteraction] usage.
 */
class DataInteractionsTest : BaseTest() {

    @Test
    fun dataInteractionSample() {
        openDrawer()
        onView(
            allOf(
                withId(R.id.design_menu_item_text),
                withText(R.string.settings_title)
            )
        ).perform(click())
        /**
         * [Espresso.onData] builds a DataInteraction object and expected to finish
         * with an [ViewActions] or [ViewAssertion].
         */
        onData(instanceOf(PreferenceActivity.Header::class.java))
            .inAdapterView(withId(android.R.id.list))
            .atPosition(0)
            .onChildView(withId(android.R.id.title))
            .check(matches(withText("General")))
            .perform(click())
        onData(withKey("email_edit_text"))
            .inAdapterView(
                allOf(
                    withId(android.R.id.list),
                    withParent(withId(android.R.id.list_container))
                )
            )
            .check(matches(isDisplayed()))
            .perform(click())
        onView(withId(android.R.id.edit)).perform(replaceText("sample@ema.il"))
        onView(withId(android.R.id.button1)).perform(click())

        onData(withKey("email_edit_text"))
            .inAdapterView(
                allOf(
                    withId(android.R.id.list),
                    withParent(withId(android.R.id.list_container))
                )
            )
            .onChildView(withId(android.R.id.summary))
            .check(matches(withText("sample@ema.il")))
    }
}
