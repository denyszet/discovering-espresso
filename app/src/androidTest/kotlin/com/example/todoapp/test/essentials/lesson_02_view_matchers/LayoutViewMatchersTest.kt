@file:Suppress("DEPRECATION")

package com.example.todoapp.test.essentials.lesson_02_view_matchers

import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.LayoutMatchers.hasEllipsizedText
import androidx.test.espresso.matcher.LayoutMatchers.hasMultilineText
import androidx.test.espresso.matcher.LayoutMatchers
import com.example.todoapp.test.essentials.BaseTest
import android.widget.TextView

/**
 * Demonstrates Espresso Layout matchers.
 */
class LayoutViewMatchersTest : BaseTest() {

    /**
     * [LayoutMatchers.hasEllipsizedText] matches target [TextView] element having ellipsized text.
     */
    @Test
    fun layoutHasEllipsizedText() {
        onView(hasEllipsizedText())
    }

    /**
     * [LayoutMatchers.hasMultilineText] matches a [TextView] with multiline text.
     */
    @Test
    fun layoutHasMultilineText() {
        onView(hasMultilineText())
    }
}
