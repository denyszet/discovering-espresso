package com.example.android.architecture.blueprints.todoapp.test.essentials.l2_viewmatchers

import android.view.inputmethod.EditorInfo
import android.widget.CheckBox

import com.example.android.architecture.blueprints.todoapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.LayoutMatchers.hasEllipsizedText
import androidx.test.espresso.matcher.LayoutMatchers.hasMultilineText
import androidx.test.espresso.matcher.PreferenceMatchers.withKey
import androidx.test.espresso.matcher.PreferenceMatchers.withSummaryText
import androidx.test.espresso.matcher.PreferenceMatchers.withTitle
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.RootMatchers.isTouchable
import androidx.test.espresso.matcher.ViewMatchers.hasContentDescription
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.hasImeAction
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isFocusable
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not

/**
 * Lists all ViewMatchers. ViewMatchers here are without functional load.
 * This is done for demonstration purposes.
 */
@RunWith(AndroidJUnit4::class)
class ViewMatchersTest {

    @Test
    fun userProperties() {
        onView(withId(R.id.fab_add_task))
        onView(withText("All TO-DOs"))
        onView(withContentDescription(R.string.menu_filter))
        onView(hasContentDescription())
        onView(withHint(R.string.name_hint))
    }

    @Test
    fun uiProperties() {
        onView(isDisplayed())
        onView(isEnabled())
        onView(isChecked())
        onView(isSelected())
    }

    @Test
    fun objectMatcher() {
        onView(not(isChecked()))
        onView(allOf(withText("item 1"), isChecked()))
    }

    @Test
    fun hierarchy() {
        onView(withParent(withId(R.id.todo_item)))
        onView(withChild(withText("item 2")))
        onView(isDescendantOfA(withId(R.id.todo_item)))
        onView(hasDescendant(isChecked()))
                .check(matches(isDisplayed()))
                .check(matches(isFocusable()))
        onView(hasSibling(withContentDescription(R.string.menu_filter)))
    }

    @Test
    fun input() {
        onView(supportsInputMethods())
        onView(hasImeAction(EditorInfo.IME_ACTION_SEND))
    }

    @Test
    fun classMatchers() {
        onView(isAssignableFrom(CheckBox::class.java))
        onView(withClassName(`is`(FloatingActionButton::class.java.canonicalName)))
    }

    @Test
    fun rootMatchers() {
        onView(isFocusable())
        onView(withText(R.string.name_hint)).inRoot(isTouchable())
        onView(withText(R.string.name_hint)).inRoot(isDialog())
        onView(withText(R.string.name_hint)).inRoot(isPlatformPopup())
    }

    @Test
    fun preferenceMatchers() {
        onData(withSummaryText("3 days"))
        onData(withTitle(R.string.pref_title_send_notifications))
        onData(withKey("example_switch"))
        onView(isEnabled())
    }

    @Test
    fun layoutMatchers() {
        onView(hasEllipsizedText())
        onView(hasMultilineText())
    }
}
