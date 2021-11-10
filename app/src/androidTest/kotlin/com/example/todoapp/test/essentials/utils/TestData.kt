package com.example.todoapp.test.essentials.utils

object ToDoItem {

    val title get() = "item " + System.currentTimeMillis()
    val description get() = "description " + System.currentTimeMillis()
}
