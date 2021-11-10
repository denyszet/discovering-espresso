@file:Suppress("DEPRECATION")

package com.example.todoapp.test.essentials.lesson_02_view_matchers

import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers
import com.example.todoapp.test.essentials.BaseTest
import org.hamcrest.CoreMatchers.`is`
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.core.view.children
import androidx.core.view.descendants
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.architecture.blueprints.todoapp.R
import com.example.todoapp.test.essentials.utils.CommonElements.descendantsWithIdInvisible
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers

/**
 * Demonstrates Espresso Class matchers.
 */
class ClassViewMatchersTest : BaseTest() {

    /**
     * [ViewMatchers.isAssignableFrom] matches a [View] based on its class.
     */
    @Test
    fun classIsAssignableFrom() {
        onView(isAssignableFrom(FloatingActionButton::class.java))
    }

    /**
     * [ViewMatchers.withClassName] matches a [View] based on its class name.
     */
    @Test
    fun classWithClassName() {
        onView(withClassName(equalTo(FloatingActionButton::class.java.name)))
    }
}
