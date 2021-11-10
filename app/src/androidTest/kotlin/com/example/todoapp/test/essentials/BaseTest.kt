package com.example.todoapp.test.essentials

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class BaseTest {

    /**
     * Selected activity will be launched before each test.
     */
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(TasksActivity::class.java)
}
