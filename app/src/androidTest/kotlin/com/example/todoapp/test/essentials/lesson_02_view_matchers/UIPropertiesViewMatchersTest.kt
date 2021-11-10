package com.example.todoapp.test.essentials.lesson_02_view_matchers

import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers
import com.example.todoapp.test.essentials.BaseTest
import android.view.View
import android.widget.CompoundButton

/**
 * Demonstrates Espresso UI Properties [ViewMatchers].
 */
class UIPropertiesViewMatchersTest : BaseTest() {

    /**
     * [ViewMatchers.isDisplayed] matches a [View] that is currently
     * displayed on the screen.
     */
    @Test
    fun uiPropertiesIsDisplayed() {
        onView(isDisplayed())
    }

    /**
     * [ViewMatchers.isCompletelyDisplayed] matches a [View] that is
     * completely displayed on the screen.
     */
    @Test
    fun uiPropertiesIsCompletelyDisplayed() {
        onView(isCompletelyDisplayed())
    }

    /**
     * [ViewMatchers.isEnabled] matches a [View] that is enabled.
     */
    @Test
    fun uiPropertiesIsEnabled() {
        onView(isEnabled())
    }

    /**
     * [ViewMatchers.hasFocus] matches a [View] that currently has focus.
     */
    @Test
    fun uiPropertiesHasFocus() {
        onView(hasFocus())
    }

    /**
     * [ViewMatchers.isClickable] matches a [View] that is clickable.
     */
    @Test
    fun uiPropertiesIsClickable() {
        onView(isClickable())
    }

    /**
     * [ViewMatchers.isChecked] matches a [CompoundButton] that is checked.
     */
    @Test
    fun uiPropertiesIsChecked() {
        onView(isChecked())
    }

    /**
     * [ViewMatchers.isNotChecked] matches a [CompoundButton] that is not checked.
     */
    @Test
    fun uiPropertiesIsNotChecked() {
        onView(isNotChecked())
    }

    /**
     * [ViewMatchers.isSelected] matches a [View] that is selected.
     */
    @Test
    fun uiPropertiesIsSelected() {
        onView(isSelected())
    }

    /**
     * [ViewMatchers.withEffectiveVisibility] matches a [View] that have
     * "effective" visibility set to the given value.
     *
     * Visibility values: [Visibility.VISIBLE], [Visibility.INVISIBLE], [Visibility.GONE]
     */
    @Test
    fun uiPropertiesWithEffectiveVisibility() {
        onView(withEffectiveVisibility(Visibility.VISIBLE))
        onView(withEffectiveVisibility(Visibility.INVISIBLE))
        onView(withEffectiveVisibility(Visibility.GONE))
    }
}
