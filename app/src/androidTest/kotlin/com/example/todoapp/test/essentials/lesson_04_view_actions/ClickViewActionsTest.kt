package com.example.todoapp.test.essentials.lesson_04_view_actions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.KeyEvent
import androidx.appcompat.widget.AppCompatTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.architecture.blueprints.todoapp.R
import com.example.todoapp.test.essentials.BaseTest
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Before
import org.junit.Test

/**
 * Demonstrates Espresso click [ViewActions] usage.
 */
class ClickViewActionsTest : BaseTest() {

    @Before
    fun setClipboardText() {
        val clipboard =
            InstrumentationRegistry.getInstrumentation().targetContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(
            "espresso essentials",
            "text to paste ${System.currentTimeMillis()}"
        )
        clipboard.setPrimaryClip(clip)
    }

    @Test
    fun clickViewActionsClick() {
        onView(withId(R.id.fab_add_task)).perform(click())
    }

    @Test
    fun clickViewActionsLongClick() {
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title)).perform(longClick())
        onView(allOf(instanceOf(AppCompatTextView::class.java), withText("Paste")))
            .inRoot(isPlatformPopup())
            .perform(click())
    }

    @Test
    fun clickViewActionsPressImeActionButton() {
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title)).perform(longClick())
        onView(allOf(instanceOf(AppCompatTextView::class.java), withText(android.R.string.paste)))
            .inRoot(isPlatformPopup())
            .perform(click())
        onView(withId(R.id.add_task_title)).perform(pressImeActionButton())
    }

    @Test
    fun clickViewActionsPressKey() {
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title)).perform(longClick())
        onView(allOf(instanceOf(AppCompatTextView::class.java), withText(android.R.string.paste)))
            .inRoot(isPlatformPopup())
            .perform(click())
        onView(withId(R.id.add_task_title)).perform(pressImeActionButton())

        onView(withId(R.id.add_task_description)).perform(longClick())
        onView(withId(R.id.add_task_description)).perform(pressKey(KeyEvent.KEYCODE_PASTE))
    }

    @Test
    fun clickViewActionsPressBack() {
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title)).perform(longClick())
        onView(allOf(instanceOf(AppCompatTextView::class.java), withText(android.R.string.paste)))
            .inRoot(isPlatformPopup())
            .perform(click())
        onView(withId(R.id.add_task_title)).perform(pressImeActionButton())
        onView(withId(R.id.add_task_description)).perform(longClick())
        onView(withId(R.id.add_task_description)).perform(pressKey(KeyEvent.KEYCODE_PASTE))

        onView(withId(R.id.add_task_description)).perform(pressBack())
        onView(withId(R.id.fab_edit_task_done)).perform(click())
    }

    @Test
    fun clickViewActionsCloseSoftKeyboard() {
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title)).perform(longClick())
        onView(allOf(instanceOf(AppCompatTextView::class.java), withText(android.R.string.paste)))
            .inRoot(isPlatformPopup())
            .perform(click())
        onView(withId(R.id.add_task_title)).perform(pressImeActionButton())
        onView(withId(R.id.add_task_description))
            .perform(
                longClick(),
                pressKey(KeyEvent.KEYCODE_PASTE)
            )

        onView(withId(R.id.add_task_description)).perform(closeSoftKeyboard())
        onView(withId(R.id.fab_edit_task_done)).perform(click())
    }

    @Test
    fun clickViewActionsPressMenuKey() {
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title)).perform(longClick())
        onView(allOf(instanceOf(AppCompatTextView::class.java), withText(android.R.string.paste)))
            .inRoot(isPlatformPopup())
            .perform(click())
        onView(withId(R.id.add_task_title)).perform(pressImeActionButton())
        onView(withId(R.id.add_task_description))
            .perform(
                longClick(),
                pressKey(KeyEvent.KEYCODE_PASTE)
            )
        onView(withId(R.id.fab_edit_task_done)).perform(click())
        onView(withId(R.id.todo_complete)).perform(click())

        onView(withId(R.id.fab_add_task)).perform(pressMenuKey())
        onView(withText(R.string.menu_clear)).perform(click())
    }

    @Test
    fun clickViewActionsOpenLinkWithText() {
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title)).perform(longClick())
        onView(allOf(instanceOf(AppCompatTextView::class.java), withText(android.R.string.paste)))
            .inRoot(isPlatformPopup())
            .perform(click())
        onView(withId(R.id.add_task_title)).perform(pressImeActionButton())
        onView(withId(R.id.add_task_description))
            .perform(
                longClick(),
                pressKey(KeyEvent.KEYCODE_PASTE),
                pressBack()
            )
        onView(withId(R.id.fab_edit_task_done)).perform(click())
        onView(withId(R.id.todo_complete)).perform(click())
        onView(withId(R.id.fab_add_task)).perform(pressMenuKey())
        onView(withText(R.string.menu_clear)).perform(click())

        onView(withId(R.id.myWebPage)).perform(openLinkWithText("https://testyour.app"))
    }
}
