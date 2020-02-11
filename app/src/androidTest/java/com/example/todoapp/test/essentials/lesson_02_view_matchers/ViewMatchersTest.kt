@file:Suppress("DEPRECATION")

package com.example.todoapp.test.essentials.lesson_02_view_matchers

import android.view.inputmethod.EditorInfo
import android.widget.CheckBox
import com.example.android.architecture.blueprints.todoapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.LayoutMatchers.hasEllipsizedText
import androidx.test.espresso.matcher.LayoutMatchers.hasMultilineText
import androidx.test.espresso.matcher.PreferenceMatchers.withKey
import androidx.test.espresso.matcher.PreferenceMatchers.withSummaryText
import androidx.test.espresso.matcher.PreferenceMatchers.withTitle
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.RootMatchers.isTouchable
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers
import com.example.todoapp.test.essentials.BaseTest
import org.hamcrest.CoreMatchers.`is`
import android.view.View
import androidx.test.espresso.Root
import android.preference.Preference

/**
 * Demonstrates all [ViewMatchers] without functional load.
 * Test cases below implemented to showcase [ViewMatchers] without any actions.
 */
@RunWith(AndroidJUnit4::class)
class ViewMatchersTest : BaseTest() {

    /**
     * [ViewMatchers] that are related to [View] properties.
     */
    @Test
    fun userProperties() {
        onView(withId(R.id.fab_add_task))
        onView(withText("All TO-DOs"))
        onView(withContentDescription(R.string.menu_filter))
        onView(hasContentDescription())
        onView(withHint(R.string.name_hint))
    }

    /**
     * [ViewMatchers] that are related to UI state of the [View].
     */
    @Test
    fun uiProperties() {
        onView(isDisplayed())
        onView(isEnabled())
        onView(isChecked())
        onView(isSelected())
    }

    /**
     * [ViewMatchers] that are used to match target [View] based on hierarchy relationships.
     */
    @Test
    fun hierarchy() {
        onView(withParent(withId(R.id.todo_item)))
        onView(withChild(withText("item 2")))
        onView(isDescendantOfA(withId(R.id.todo_item)))
        onView(hasDescendant(isChecked()))
        onView(hasSibling(withContentDescription(R.string.menu_filter)))
    }

    /**
     * [ViewMatchers] that are used to match target [View] based on input methods/actions.
     */
    @Test
    fun input() {
        onView(supportsInputMethods())
        onView(hasImeAction(EditorInfo.IME_ACTION_SEND))
    }

    /**
     * [ViewMatchers] that are used to match target [View] based on its class.
     */
    @Test
    fun classMatchers() {
        onView(isAssignableFrom(CheckBox::class.java))
        onView(withClassName(`is`(FloatingActionButton::class.java.canonicalName)))
    }

    /**
     * [ViewMatchers] that are used to match target [View] inside the [Root] view.
     */
    @Test
    fun rootMatchers() {
        onView(isFocusable())
        onView(withText(R.string.name_hint)).inRoot(isTouchable())
        onView(withText(R.string.name_hint)).inRoot(isDialog())
        onView(withText(R.string.name_hint)).inRoot(isPlatformPopup())
    }

    /**
     * [ViewMatchers] that are used to match target [View] inside the [Preference].
     * Be aware that [Preference] class is deprecated.
     */
    @Test
    fun preferenceMatchers() {
        onData(withSummaryText("3 days"))
        onData(withTitle(R.string.pref_title_send_notifications))
        onData(withKey("example_switch"))
        onView(isEnabled())
    }

    /**
     * [ViewMatchers] that are used to match target [View] based on layout properties.
     */
    @Test
    fun layoutMatchers() {
        onView(hasEllipsizedText())
        onView(hasMultilineText())
    }
}
