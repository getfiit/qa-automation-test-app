package com.example.fiitqatest.domain

data class Workout(
    val id: Int,
    val name: String,
    val duration: String,
    val imageUrl: String
)

data class WorkoutDetails(
    val id: Int,
    val name: String,
    val duration: String,
    val difficulty: String,
    val description: String,
    val imageUrl: String
)
