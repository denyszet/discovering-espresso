@file:Suppress("DEPRECATION")

package com.example.todoapp.test.essentials.lesson_02_view_matchers

import android.view.inputmethod.EditorInfo
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers
import com.example.todoapp.test.essentials.BaseTest
import android.view.View

/**
 * Demonstrates Espresso Input view matchers.
 */
class InputViewMatchersTest : BaseTest() {

    /**
     * [ViewMatchers.supportsInputMethods] matches a [View] with supported input methods.
     */
    @Test
    fun inputSupportsInputMethods() {
        onView(supportsInputMethods())
    }

    /**
     * [ViewMatchers.hasImeAction] matches a [View] with supported input methods and specified IME action.
     */
    @Test
    fun inputHasImeAction() {
        onView(hasImeAction(EditorInfo.IME_ACTION_NEXT))
        onView(hasImeAction(EditorInfo.IME_ACTION_SEARCH))
        onView(hasImeAction(EditorInfo.IME_ACTION_DONE))
    }
}
