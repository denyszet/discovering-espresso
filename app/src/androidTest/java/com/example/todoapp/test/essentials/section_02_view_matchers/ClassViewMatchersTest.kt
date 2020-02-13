@file:Suppress("DEPRECATION")

package com.example.todoapp.test.essentials.section_02_view_matchers

import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers
import com.example.todoapp.test.essentials.BaseTest
import org.hamcrest.CoreMatchers.`is`
import android.view.View
import android.widget.EditText

/**
 * Demonstrates Espresso Class matchers.
 */
class ClassViewMatchersTest : BaseTest() {

    /**
     * [ViewMatchers.isAssignableFrom] matches a [View] based on its class.
     */
    @Test
    fun classIsAssignableFrom() {
        onView(isAssignableFrom(EditText::class.java))
    }

    /**
     * [ViewMatchers.withClassName] matches a [View] based on its class name.
     */
    @Test
    fun classWithClassName() {
        onView(withClassName(`is`(FloatingActionButton::class.java.canonicalName)))
    }
}
