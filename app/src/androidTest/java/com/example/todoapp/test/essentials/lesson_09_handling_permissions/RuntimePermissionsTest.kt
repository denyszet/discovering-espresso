package com.example.todoapp.test.essentials.lesson_09_handling_permissions

import android.Manifest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.todoapp.test.essentials.utils.TestData
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Showcases [GrantPermissionRule] functionality.
 */
@RunWith(AndroidJUnit4::class)
class RuntimePermissionsTest {

    /**
     * Manifest.permission.CAMERA permission will be granted before the test run.
     */
    @get:Rule
    var mRuntimePermissionRule: GrantPermissionRule = GrantPermissionRule
            .grant(Manifest.permission.CAMERA)

    /**
     * Provided activity will be launched before each test.
     */
    @get:Rule
    var activityTestRule = ActivityTestRule(TasksActivity::class.java)

    @Test
    fun takesCameraPicture() {
        val toDoTitle = TestData.getToDoTitle()
        val toDoDescription = TestData.getToDoDescription()

        // Add new TO-DO.
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard())
        onView(withId(R.id.add_task_description))
                .perform(typeText(toDoDescription), closeSoftKeyboard())

        // Clicking on camera button to trigger the permission dialog.
        onView(withId(R.id.makePhoto)).perform(click())
        onView(withId(R.id.picture)).perform(click())
        onView(withId(R.id.fab_edit_task_done)).perform(click())
    }
}
