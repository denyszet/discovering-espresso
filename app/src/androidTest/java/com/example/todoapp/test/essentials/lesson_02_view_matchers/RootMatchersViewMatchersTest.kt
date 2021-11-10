package com.example.todoapp.test.essentials.lesson_02_view_matchers

import com.example.android.architecture.blueprints.todoapp.R
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import com.example.todoapp.test.essentials.BaseTest
import android.widget.PopupWindow
import androidx.test.espresso.Root
import androidx.test.espresso.matcher.RootMatchers.*
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withText

/**
 * Demonstrates Espresso [RootMatchers].
 */
class RootMatchersViewMatchersTest : BaseTest() {

    /**
     * [RootMatchers.isFocusable] matches a [Root] view that is focusable.
     */
    @Test
    fun rootMatchersIsFocusable() {
        onView(withText(R.string.menu_clear)).inRoot(isFocusable())
    }

    /**
     * [RootMatchers.isTouchable] matches a [Root] view that is touchable.
     */
    @Test
    fun rootMatchersIsTouchable() {
        onView(withText(R.string.menu_clear)).inRoot(isTouchable())
    }

    /**
     * [RootMatchers.isDialog] matches a [Root] view that is dialog.
     */
    @Test
    fun rootMatchersIsDialog() {
        onView(withHint(R.string.name_hint)).inRoot(isDialog())
    }

    /**
     * [RootMatchers.isPlatformPopup] matches a [Root] view that is platform pop-up like [PopupWindow].
     */
    @Test
    fun rootMatchersIsPlatformPopup() {
        onView(withText("Paste")).inRoot(isPlatformPopup())
    }
}
