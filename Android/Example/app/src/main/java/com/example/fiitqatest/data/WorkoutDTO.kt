package com.example.fiitqatest.data

data class WorkoutRoot(
    val workouts: List<WorkoutDTO>
) {
    data class WorkoutDTO(
        val id: Int,
        val name: String,
        val duration: String,
        val difficulty: String,
        val description: String,
        val imageUrl: String
    )
}
