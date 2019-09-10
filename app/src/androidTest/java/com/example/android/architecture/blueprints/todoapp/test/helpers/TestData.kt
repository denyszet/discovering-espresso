package com.example.android.architecture.blueprints.todoapp.test.helpers;

object TestData {

    fun getToDoTitle(): String {
        return "item " + System.currentTimeMillis();
    }

    fun getToDoDescription(): String {
        return "description " + System.currentTimeMillis();
    }
}
