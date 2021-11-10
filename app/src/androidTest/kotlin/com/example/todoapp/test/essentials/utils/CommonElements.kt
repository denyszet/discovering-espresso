package com.example.todoapp.test.essentials.utils

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.annotation.IdRes
import androidx.core.view.descendants
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.util.HumanReadables
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.architecture.blueprints.todoapp.R
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf.allOf

object CommonElements {

    private val HAMBURGER_BUTTON = onView(
        allOf(
            instanceOf(ImageButton::class.java),
            withParent(withId(R.id.toolbar))
        )
    )

    @JvmStatic
    fun openDrawer(): ViewInteraction {
        return HAMBURGER_BUTTON.perform(click())
    }

    fun descendantsWithIdInvisible(@IdRes id: Int) = DescendantsWithIdInvisibleViewAssertion(id)

    class DescendantsWithIdInvisibleViewAssertion(@IdRes id: Int) : ViewAssertion {
        private val descendantId = id
        private val resourceName = InstrumentationRegistry.getInstrumentation()
            .targetContext.resources.getResourceEntryName(descendantId)

        override fun check(view: View?, noView: NoMatchingViewException?) {
            assertTrue(
                "Ancestor view should be displayed in the hierarchy but it wasn't",
                isDisplayed().matches(view)
            )
            var visibleViewExist = false
            (view as ViewGroup).descendants.forEach { descendantView ->
                val matches =
                    withId(descendantId).matches(descendantView)
                        .and(withEffectiveVisibility(Visibility.VISIBLE).matches(descendantView))
                if (matches) {
                    visibleViewExist = true
                }
            }
            assertFalse(
                "View with id: $resourceName was displayed but it shouldn't.",
                visibleViewExist
            )
        }
    }
}
