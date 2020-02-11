package com.example.todoapp.test.essentials.utils;

object TestData {

    fun getToDoTitle(): String {
        return "item " + System.currentTimeMillis();
    }

    fun getToDoDescription(): String {
        return "description " + System.currentTimeMillis();
    }
}
