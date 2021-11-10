package com.example.todoapp.test.essentials.lesson_10_kotlin_espressodsl

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Inline functions
 */
fun viewWithText(text: String): ViewInteraction = Espresso.onView(withText(text))

fun viewWithText(stringId: Int): ViewInteraction = Espresso.onView(withText(stringId))

fun viewWithId(id: Int): ViewInteraction = Espresso.onView(withId(id))

fun onAnyData(): DataInteraction = Espresso.onData(anything())

fun dataInstanceOf(clazz: Class<*>): DataInteraction = Espresso.onData(instanceOf(clazz))

/**
 * [ViewActions] extensions
 */
fun ViewInteraction.click(): ViewInteraction = perform(ViewActions.click())

fun ViewInteraction.type(text: String): ViewInteraction = perform(typeText(text))

fun ViewInteraction.replace(text: String): ViewInteraction = perform(replaceText(text))

fun ViewInteraction.closeKeyboard(): ViewInteraction = perform(closeSoftKeyboard())

/**
 * [ViewInteraction] extensions
 */
fun ViewInteraction.checkHasChildByText(text: String): ViewInteraction =
    check(ViewAssertions.matches(withChild(withText(text))))

fun ViewInteraction.checkHasChildByText(id: Int): ViewInteraction =
    check(ViewAssertions.matches(withChild(withText(id))))

fun ViewInteraction.checkHasChildById(id: Int): ViewInteraction =
    check(ViewAssertions.matches(withChild(withId(id))))

fun ViewInteraction.checkDisplayed(): ViewInteraction =
    check(ViewAssertions.matches(isDisplayed()))

fun ViewInteraction.checkNotDisplayed(): ViewInteraction =
    check(ViewAssertions.matches(not(isDisplayed())))

fun ViewInteraction.checkDoesNotExist(): ViewInteraction =
    check(ViewAssertions.doesNotExist())

fun ViewInteraction.checkMatches(matcher: Matcher<View>): ViewInteraction =
    check(ViewAssertions.matches(matcher))

/**
 * Expand function for web view test case.
 * It contains a Thread.sleep() each time key event is sent.
 *
 * @param key - keycode from {@link KeyEvent}
 * @param milliseconds - milliseconds to sleep
 * @param count - amount of times {@link KeyEvent} should be executed
 */
fun ViewInteraction.sleepAndPressKey(
    key: Int,
    milliseconds: Long,
    count: Int = 1
): ViewInteraction {
    for (i in 1..count) {
        /**
         * Having Thread.sleep() in tests is a bad practice.
         * Here we are using it just to solve specific issue and nothing more.
         */
        Thread.sleep(milliseconds)
        perform(pressKey(key))
    }
    return this
}

/**
 * Aggregated matchers
 */
fun ViewInteraction.allOf(vararg matcher: Matcher<View>): ViewInteraction {
    return check(ViewAssertions.matches(Matchers.allOf(matcher.asIterable())))
}

/**
 * [DataInteraction] extensions
 */
fun DataInteraction.click(): ViewInteraction = perform(ViewActions.click())

fun DataInteraction.checkDisplayed(): ViewInteraction =
    check(ViewAssertions.matches(isDisplayed()))

fun DataInteraction.checkWithText(text: String): ViewInteraction =
    check(ViewAssertions.matches(withText(text)))

fun DataInteraction.checkMatches(matcher: Matcher<View>): ViewInteraction =
    check(ViewAssertions.matches(matcher))

fun DataInteraction.childById(id: Int): DataInteraction = onChildView(withId(id))

fun DataInteraction.inAdapterById(id: Int): DataInteraction = inAdapterView(withId(id))

/**
 * [RecyclerViewActions] extensions
 */
fun ViewInteraction.actionAtPosition(
    position: Int,
    action: ViewAction
): ViewInteraction =
    perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, action))

fun ViewInteraction.scrollToPosition(position: Int): ViewInteraction =
    perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))

fun ViewInteraction.scrollTo(matcher: Matcher<View>): ViewInteraction =
    perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(matcher))

/**
 * [ViewMatchers]
 */
fun parentWithId(id: Int): Matcher<View> = withParent(withId(id))
