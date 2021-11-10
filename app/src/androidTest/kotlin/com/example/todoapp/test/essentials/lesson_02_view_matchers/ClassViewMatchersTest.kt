@file:Suppress("DEPRECATION")

package com.example.todoapp.test.essentials.lesson_02_view_matchers

import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers
import com.example.todoapp.test.essentials.BaseTest
import android.view.View
import org.hamcrest.CoreMatchers.equalTo

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
