### Building and installing the main and test applications

Start from building and installing application under test and test application on the device. Skip this step if you already installed the main application and run UI tests from the Android Studio IDE.

Build main and test apps using gradle commands:

    ./gradlew assembleMockDebug
    ./gradlew assembleMockDebugAndroidTest

> Note that **MockDebug** part from assembleMockDebugAndroidTest is the build variant and it may be different in other projects. 

Install main and test apps:

    ./gradlew installMockDebug
    ./gradlew installMockDebugAndroidTest


### List instrumented packages

Run below command to see available instrumentations on the device:

    adb shell pm list instrumentation

### Running tests with adb commands

Official documentation - https://developer.android.com/studio/test/command-line#am-instrument-flags

##### Running tests under a specific package:
    adb shell am instrument -w \
        -e package com.example.todoapp.test.essentials.lesson_04_view_actions \
        com.example.android.architecture.blueprints.todoapp.mock.test/androidx.test.runner.AndroidJUnitRunner

##### Running tests under a specific class:
    adb shell am instrument -w \
        -e class com.example.todoapp.test.essentials.lesson_04_view_actions.ClickViewActionsTest \
        com.example.android.architecture.blueprints.todoapp.mock.test/androidx.test.runner.AndroidJUnitRunner

##### Running a single test case:
    adb shell am instrument -w \
        -e class com.example.todoapp.test.essentials.lesson_04_view_actions.ClickViewActionsTest#clickViewActionsPressImeActionButton \
        com.example.android.architecture.blueprints.todoapp.mock.test/androidx.test.runner.AndroidJUnitRunner

##### Running tests annotated with @SmallTest, @MediumTest and @LargeTest annotations:
    adb shell am instrument -w \
        -e size small \
        com.example.android.architecture.blueprints.todoapp.mock.test/androidx.test.runner.AndroidJUnitRunner

##### Running tests in a test suite:
    adb shell am instrument -w \
        -e class com.example.todoapp.test.essentials.lesson_13_running_tests.TestSuiteSample \
        com.example.android.architecture.blueprints.todoapp.mock.test/androidx.test.runner.AndroidJUnitRunner


### Running tests with gradle commands

##### Running tests under a specific package:
    ./gradlew app:connectedMockDebugAndroidTest \
        -Pandroid.testInstrumentationRunnerArguments.package=com.example.todoapp.test.essentials.lesson_04_view_actions

##### Running tests under a specific class:
    ./gradlew app:connectedMockDebugAndroidTest \
        -Pandroid.testInstrumentationRunnerArguments.class=com.example.todoapp.test.essentials.lesson_04_view_actions.ClickViewActionsTest

##### Running a single test case:
    ./gradlew app:connectedMockDebugAndroidTest \
        -Pandroid.testInstrumentationRunnerArguments.class=com.example.todoapp.test.essentials.lesson_04_view_actions.ClickViewActionsTest#clickViewActionsPressImeActionButton 

##### Running tests annotated with @SmallTest, @MediumTest and @LargeTest annotations:
    ./gradlew app:connectedMockDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.size=large

##### Running tests under a specific test suite:
    ./gradlew app:connectedMockDebugAndroidTest \
        -Pandroid.testInstrumentationRunnerArguments.class=com.example.todoapp.test.essentials.lesson_13_running_tests.TestSuiteSample
