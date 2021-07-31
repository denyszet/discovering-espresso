package com.example.todoapp.test.essentials

import android.Manifest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.Rule
import org.junit.rules.RuleChain
import org.junit.rules.TestName
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class BaseTest {

    /**
     * Selected activity will be launched before each test.
     */
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(TasksActivity::class.java)
}
