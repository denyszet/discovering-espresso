@file:Suppress("DEPRECATION")

package com.example.todoapp.test.essentials.lesson_02_view_matchers

import com.example.android.architecture.blueprints.todoapp.R
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.todoapp.test.essentials.BaseTest
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers.*

/**
 * Demonstrates Espresso User properties matchers.
 */
class UserPropertiesViewMatchersTest : BaseTest() {

    /**
     * [ViewMatchers.withId] matches a [View] based on id.
     */
    @Test
    fun userPropertiesWithId() {
        onView(withId(R.id.fab_add_task))
    }

    /**
     * [ViewMatchers.withText] matches a [View] based on text.
     */
    @Test
    fun userPropertiesWithText() {
        onView(withText("You have no TO-DOs!"))
        onView(withText(R.string.no_tasks_all))
    }

    /**
     * [ViewMatchers.withTagKey] matches a [View] based on tag key.
     */
    @Test
    fun userPropertiesWithTagKey() {
        onView(withTagKey(R.string.title_hint))
    }

    /**
     * [ViewMatchers.withTagValue] matches a [View] based on tag value.
     */
    @Test
    fun userPropertiesWithTagValue() {
        onView(withTagValue(`is`("Title")))
    }

    /**
     * [ViewMatchers.hasContentDescription] matches a [View] based on existence of content description.
     */
    @Test
    fun userPropertiesHasContentDescriptions() {
        onView(hasContentDescription())
    }

    /**
     * [ViewMatchers.withContentDescription] matches a [View] with given content description.
     */
    @Test
    fun userPropertiesWithContentDescription() {
        onView(withContentDescription("Filter"))
        onView(withContentDescription(R.string.menu_filter))
    }

    /**
     * [ViewMatchers.withHint] matches a [View] with specified hint text.
     */
    @Test
    fun userPropertiesWithHint() {
        onView(withHint("Enter your TO-DO here."))
        onView(withHint(R.string.description_hint))
        onView(withHint(nullValue(String::class.java)))
    }

    /**
     * [ViewMatchers.withSpinnerText] matches a [Spinner] based on selected item.
     */
    @Test
    fun userPropertiesWithSpinnerText() {
        onView(withSpinnerText("List of TO-DOs"))
        onView(withSpinnerText(R.string.statistics_spinner_hint))
    }

    /**
     * [ViewMatchers.hasLinks] matches a [TextView] that has links.
     */
    @Test
    fun userPropertiesHasLinks() {
        onView(hasLinks())
    }
}
