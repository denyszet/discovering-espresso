package com.example.todoapp.test.essentials.lesson_13_running_tests.testsuite

import com.example.todoapp.test.essentials.lesson_04_view_actions.ClickViewActionsTest
import com.example.todoapp.test.essentials.lesson_04_view_actions.TextViewActionsTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Organising test classes into test suite.
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    ClickViewActionsTest::class,
    TextViewActionsTest::class
)
class TestSuiteSample {}
