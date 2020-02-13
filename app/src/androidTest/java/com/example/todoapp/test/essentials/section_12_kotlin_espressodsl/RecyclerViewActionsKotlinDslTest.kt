package com.example.todoapp.test.essentials.section_12_kotlin_espressodsl

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import com.example.android.architecture.blueprints.todoapp.R.id.*
import com.example.todoapp.test.essentials.BaseTest
import org.junit.Test

/**
 * Demonstrates RecyclerView actions usage with Kotlin Espresso DSL.
 */
class RecyclerViewActionsKotlinDslTest : BaseTest() {

    //ViewInteractions used in tests
    private val todoList = viewWithId(tasks_list)
    private val addFab = viewWithId(fab_add_task)
    private val taskTitleField = viewWithId(add_task_title)
    private val taskDescriptionField = viewWithId(add_task_description)
    private val editDoneFab = viewWithId(fab_edit_task_done)

    @Test
    fun addNewToDosChained() {
        12.generateToDos()
        todoList
                .actionAtPosition(10, scrollTo())
                .scrollToPosition(1)
                .scrollToPosition(11)
                .actionAtPosition(11, click())
        Espresso.pressBack()
        todoList
                .scrollToPosition(1)
        viewWithText("item 2").click()
    }

    private fun Int.generateToDos() {
        for (i in 1..this) {
            val toDoTitle = "item $i"
            val toDoDescription = "description $i"
            // adding new TO-DO
            addFab.click()
            taskTitleField.type(toDoTitle).closeKeyboard()
            taskDescriptionField.type(toDoDescription).closeKeyboard()
            editDoneFab.click()
        }
    }
}
