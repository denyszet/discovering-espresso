@file:Suppress("DEPRECATION")

package com.example.todoapp.test.essentials.lesson_11_espresso_web

import android.preference.PreferenceActivity
import android.view.KeyEvent
import android.webkit.WebView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.web.assertion.WebViewAssertions.webMatches
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.*
import androidx.test.espresso.web.webdriver.Locator
import com.example.android.architecture.blueprints.todoapp.R
import com.example.todoapp.test.essentials.BaseTest
import com.example.todoapp.test.essentials.lesson_10_kotlin_espressodsl.click
import com.example.todoapp.test.essentials.lesson_10_kotlin_espressodsl.pressKeyAndSleep
import com.example.todoapp.test.essentials.lesson_10_kotlin_espressodsl.sleepAndPressKey
import com.example.todoapp.test.essentials.utils.CommonElements.openDrawer
import org.hamcrest.CoreMatchers.*
import org.junit.Test

/**
 * Contains tests for a [WebView] pages inside the TO-DO sample app.
 */
class WebViewTest : BaseTest() {

    @Test
    fun updatesLabelAndOpensNewPage() {
        openDrawer()
        onView(
            allOf(
                withId(R.id.design_menu_item_text),
                withText(R.string.settings_title)
            )
        ).perform(click())
        onData(instanceOf(PreferenceActivity.Header::class.java))
            .inAdapterView(withId(android.R.id.list))
            .atPosition(3)
            .perform(click())
        onWebView()
            .forceJavascriptEnabled()
            // Find edit text and type text.
            .withElement(findElement(Locator.ID, "text_input"))
            .perform(webKeys("Espresso WebView testing"))
            // Find button by id and click.
            .withElement(findElement(Locator.ID, "submit_btn"))
            .perform(webClick())
            // Find element by id and check its text.
            .withElement(findElement(Locator.ID, "response"))
            .check(webMatches(getText(), equalTo("Espresso+WebView+testing")))
    }

    @Test
    fun selectsRadioButtonWithCss() {
        openDrawer()
        onView(
            allOf(
                withId(R.id.design_menu_item_text),
                withText(R.string.settings_title)
            )
        ).perform(click())
        onData(instanceOf(PreferenceActivity.Header::class.java))
            .inAdapterView(withId(android.R.id.list))
            .atPosition(3)
            .perform(click())
        onWebView()
            // Find radio button by CSS.
            .withElement(findElement(Locator.CSS_SELECTOR, "input[value=\"rb1\"]"))
            .perform(webClick())
    }

    @Test
    fun findsElementsByXpath() {
        openDrawer()
        onView(
            allOf(
                withId(R.id.design_menu_item_text),
                withText(R.string.settings_title)
            )
        ).perform(click())
        onData(instanceOf(PreferenceActivity.Header::class.java))
            .inAdapterView(withId(android.R.id.list))
            .atPosition(3)
            .perform(click())
        onWebView()
            // Find label XPATH and check its text.
            .withElement(findElement(Locator.XPATH, "//label[@id=\"selection_result\"]"))
            .perform(webScrollIntoView())
            .check(webMatches(getText(), equalTo("Select option")))
    }

    @Test
    fun opensModal() {
        openDrawer()
        onView(
            allOf(
                withId(R.id.design_menu_item_text),
                withText(R.string.settings_title)
            )
        ).perform(click())
        onData(instanceOf(PreferenceActivity.Header::class.java))
            .inAdapterView(withId(android.R.id.list))
            .atPosition(3)
            .perform(click())
        onWebView()
            // Find button and click.
            .withElement(findElement(Locator.ID, "updateDetails"))
            .perform(webClick())
            // Find edit text field and input text in the popped up dialog.
            .withElement(findElement(Locator.ID, "modal_text_input"))
            .perform(webKeys("Text from modal"))
            // Find and click Confirm button.
            .withElement(findElement(Locator.ID, "confirm"))
            .perform(webClick())
            // Verify text from modal is set in label.
            .withElement(findElement(Locator.ID, "modal_message"))
            .check(webMatches(getText(), equalTo("Text from modal")))
    }

    /**
     * This test should fail intentionally.
     */
    @Test
    fun failsToClickSelectDropDown() {
        openDrawer()
        onView(
            allOf(
                withId(R.id.design_menu_item_text),
                withText(R.string.settings_title)
            )
        ).perform(click())
        onData(instanceOf(PreferenceActivity.Header::class.java))
            .inAdapterView(withId(android.R.id.list))
            .atPosition(3)
            .perform(click())
        onWebView()
            // Supposed to click on select.
            .withElement(findElement(Locator.ID, "selection_id"))
            .perform(webClick())
            // Select list is not shown, so test fails.
            .withElement(findElement(Locator.ID, "selection_result"))
            .check(webMatches(getText(), equalTo("Item 3")))
    }

    @Test
    fun verifiesSelectDropDown() {
        openDrawer()
        onView(
            allOf(
                withId(R.id.design_menu_item_text),
                withText(R.string.settings_title)
            )
        ).perform(click())
        onData(instanceOf(PreferenceActivity.Header::class.java))
            .inAdapterView(withId(android.R.id.list))
            .atPosition(3)
            .perform(click())
        onView(withId(R.id.web_view))
            // Send TAB keys as many times as needed to reach the "select".
            .sleepAndPressKey(KeyEvent.KEYCODE_TAB, 500, 6)
            // Send SPACE key to expand "select".
            .pressKeyAndSleep(KeyEvent.KEYCODE_SPACE, 500)

        /**
         * At this point android platform popup is shown.
         * Use Espresso native methods to select item from the list.
         */
        onView(allOf(withId(android.R.id.text1), withText("Item 3"))).perform(click())
        onWebView()
            // Check that text from select list is set into the label.
            .withElement(findElement(Locator.ID, "selection_result"))
            .check(webMatches(getText(), equalTo("Item 3")))
    }

    @Test
    fun showsOtherLocatorsSample() {
        openDrawer()
        onView(
            allOf(
                withId(R.id.design_menu_item_text),
                withText(R.string.settings_title)
            )
        ).perform(click())
        onData(instanceOf(PreferenceActivity.Header::class.java))
            .inAdapterView(withId(android.R.id.list))
            .atPosition(3)
            .perform(click())
        onWebView()
            // Find element by Locator.NAME
            .withElement(findElement(Locator.NAME, "text_input"))
            .perform(webScrollIntoView())
            // Find element by Locator.LINK_TEXT
            .withElement(findElement(Locator.LINK_TEXT, "Espresso Web."))
            .perform(webScrollIntoView())
            // Find element by Locator.PARTIAL_LINK_TEXT
            .withElement(findElement(Locator.PARTIAL_LINK_TEXT, "Espresso"))
            .perform(webScrollIntoView())
            // Find element by Locator.CLASS_NAME
            .withElement(findElement(Locator.CLASS_NAME, "header"))
            .check(webMatches(webScrollIntoView(), `is`(true)))
    }
}
