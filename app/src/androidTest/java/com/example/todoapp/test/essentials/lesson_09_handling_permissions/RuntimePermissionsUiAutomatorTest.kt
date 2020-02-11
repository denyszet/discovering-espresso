package com.example.todoapp.test.essentials.lesson_09_handling_permissions

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.todoapp.test.essentials.utils.TestData
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Demonstrates how UI Automator API can be used to deal with permission dialogs.
 */
@RunWith(AndroidJUnit4::class)
class RuntimePermissionsUiAutomatorTest {

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)
    private val todoAppPackageName = InstrumentationRegistry.getInstrumentation().targetContext.packageName
    private val testContext = InstrumentationRegistry.getInstrumentation().context
    private val fabDone = "com.android.permissioncontroller:id/fab_edit_task_done"
    private val allowPermissionButtonId = "com.android.permissioncontroller:id/permission_allow_button"
    private val denyPermissionButtonId = "com.android.permissioncontroller:id/permission_deny_button"
    private val denyAndDontAskPermissionButtonId = "com.android.permissioncontroller:id/permission_deny_and_dont_ask_again_button"

    /**
     * Provided activity will be launched before each test.
     */
    @get:Rule
    var activityTestRule = ActivityTestRule(TasksActivity::class.java)

    @Test
    fun takesCameraPicture() {
        val toDoTitle = TestData.getToDoTitle()

        // Add new TO-DO.
        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard())

        // Click on camera button to trigger the permission dialog.
        onView(withId(R.id.makePhoto)).perform(click())

        // UIAutomator - click permission dialog ALLOW button.
        uiDevice.findObject(By.res(allowPermissionButtonId)).click()

        onView(withId(R.id.picture)).perform(click())
        uiDevice.wait(Until.hasObject(By.res(fabDone)), 2000)
        onView(withId(R.id.fab_edit_task_done)).perform(click())

        // Verify new TO-DO with title is shown in the TO-DO list.
        onView(withText(toDoTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun deniesAndGrantsPermission() {
        val toDoTitle = TestData.getToDoTitle()

        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard())
        onView(withId(R.id.makePhoto)).perform(click())

        // UIAutomator - click permission dialog DENY button.
        uiDevice.findObject(By.res(denyPermissionButtonId)).click()

        onView(withId(R.id.makePhoto)).perform(click())
        onView(withId(R.id.snackbar_action)).perform(click())

        uiDevice.findObject(By.res(allowPermissionButtonId)).click()

        onView(withId(R.id.picture)).perform(click())
        uiDevice.wait(Until.hasObject(By.res(fabDone)), 2000)
        onView(withId(R.id.fab_edit_task_done)).perform(click())

        onView(withText(toDoTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun deniesAndGrantsPermissionFromSettings() {
        val toDoTitle = TestData.getToDoTitle()

        onView(withId(R.id.fab_add_task)).perform(click())

        // Since the title EditText has focus keyboard appears and we have to close it
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.makePhoto)).perform(click())

        uiDevice.findObject(By.res(denyPermissionButtonId))
                .click()

        onView(withId(R.id.makePhoto)).perform(click())
        onView(withId(R.id.snackbar_action)).perform(click())

        // UIAutomator - click on permission dialog checkbox and DENY button
        uiDevice
                .findObject(By.res(denyAndDontAskPermissionButtonId))
                .click()

        // Click camera button to trigger permission dialog.
        onView(withId(R.id.makePhoto)).perform(click())
        onView(withId(R.id.snackbar_text))
                .check(matches(allOf(isDisplayed(), withText("Camera unavailable"))))

        sendApplicationSettingsIntent()
        enableCameraPermission()
        launchBackToDoApplication()

        onView(withId(R.id.fab_add_task)).perform(click())
        onView(withId(R.id.add_task_title))
                .perform(typeText(toDoTitle), closeSoftKeyboard())
        onView(withId(R.id.makePhoto)).perform(click())
        onView(withId(R.id.picture)).perform(click())
        // Wait for application Settings to appear
        uiDevice.wait(Until.hasObject(By.res(fabDone)), 2000)
        onView(withId(R.id.fab_edit_task_done)).perform(click())
        onView(withText(toDoTitle)).check(matches(isDisplayed()))
    }

    private fun sendApplicationSettingsIntent() {
        // Create intent to open To-Do application settings.
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", todoAppPackageName, null)
        intent.data = uri
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        testContext.startActivity(intent)
    }

    private fun launchBackToDoApplication() {

        // Create intent to open To-Do application.
        val intent = testContext.packageManager.getLaunchIntentForPackage(todoAppPackageName)
        InstrumentationRegistry.getInstrumentation().context.startActivity(intent)
    }

    private fun enableCameraPermission() {

        // Wait for application Settings to appear
        uiDevice.wait(Until.hasObject(By.pkg("com.android.settings")), 5000)

        // Click on Permissions item.
        uiDevice.findObject(By.res("com.android.settings:id/recycler_view"))
                .children[3].clickAndWait(Until.newWindow(), 2000)

        // CLick on Camera item and wait for checked toggle state.
        uiDevice.findObject(By.res("com.android.permissioncontroller:id/recycler_view"))
                .children[3].clickAndWait(Until.newWindow(), 2000)
        uiDevice.findObject(By.res("com.android.permissioncontroller:id/allow_radio_button"))
                .click()
    }
}
