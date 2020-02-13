@file:Suppress("DEPRECATION")

package com.example.todoapp.test.essentials.section_02_view_matchers

import com.example.android.architecture.blueprints.todoapp.R
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers
import com.example.todoapp.test.essentials.BaseTest
import android.view.View
import androidx.test.espresso.Root

/**
 * Demonstrates Espresso Hierarchy matchers.
 */
class HierarchyViewMatchersTest : BaseTest() {

    /**
     * [ViewMatchers.withParent] matches a [View] based on its parent.
     */
    @Test
    fun hierarchyWithParent() {
        onView(withParent(withId(R.id.statisticsSpinner)))
    }

    /**
     * [ViewMatchers.withChild] matches a [View] based on its child.
     */
    @Test
    fun hierarchyWithChild() {
        onView(withChild(withText("item 2")))
    }

    /**
     * [ViewMatchers.isDescendantOfA] matches a [View] based on the given ancestor.
     */
    @Test
    fun hierarchyIsDescendantOfA() {
        onView(isDescendantOfA(withId(R.id.todo_item)))
    }

    /**
     * [ViewMatchers.hasDescendant] matches a [View] based on its descendants existence.
     */
    @Test
    fun hierarchyHasDescendant() {
        onView(hasDescendant(isChecked()))
    }

    /**
     * [ViewMatchers.isRoot] matches a [Root] view in the hierarchy.
     */
    @Test
    fun hierarchyIsRoot() {
        onView(isRoot())
    }
}
