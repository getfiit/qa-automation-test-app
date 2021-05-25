package com.example.fiitqatest.ui.main

sealed class MainContent {
    data class WorkoutItem(
        val id: Int,
        val name: String,
        val imageUrl: String,
        val duration: String
    ) : MainContent()

    object LogoutButton : MainContent()
}
