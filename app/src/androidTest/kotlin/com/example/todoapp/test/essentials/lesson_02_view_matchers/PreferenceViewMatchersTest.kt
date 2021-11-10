@file:Suppress("DEPRECATION")
package com.example.todoapp.test.essentials.lesson_02_view_matchers

import com.example.android.architecture.blueprints.todoapp.R
import org.junit.Test
import androidx.test.espresso.Espresso.onData
import com.example.todoapp.test.essentials.BaseTest
import androidx.preference.Preference
import androidx.test.espresso.matcher.PreferenceMatchers.*
import androidx.test.espresso.matcher.PreferenceMatchers

/**
 * Demonstrates Espresso [Preference] matchers.
 */
class PreferenceViewMatchersTest : BaseTest() {

    /**
     * [PreferenceMatchers.withTitle] and [PreferenceMatchers.withTitleText] match
     * [Preference] by title.
     */
    @Test
    fun preferenceMatchersWithTitle() {
        onData(withTitle(R.string.pref_title_send_notifications))
        onData(withTitleText("Send notification"))
    }

    /**
     * [PreferenceMatchers.withSummary] and [PreferenceMatchers.withSummaryText] match
     * [Preference] by summary text.
     */
    @Test
    fun preferenceMatchersWithSummaryText() {
        onData(withSummary(R.string.slider_three_days))
        onData(withSummaryText("3 days"))
    }

    /**
     * [PreferenceMatchers.withKey] matches a [Preference] by key.
     */
    @Test
    fun preferenceMatchersWithKey() {
        onData(withKey("email_edit_text"))
    }

    /**
     * [PreferenceMatchers.isEnabled] matches a [Preference] in enabled state.
     */
    @Test
    fun preferenceMatchersIsEnabled() {
        onData(isEnabled())
    }
}
