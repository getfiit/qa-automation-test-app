package com.example.fiitqatest.data

import com.example.fiitqatest.domain.Workout
import com.example.fiitqatest.domain.WorkoutDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface WorkoutRepository {
    suspend fun getWorkouts(): List<Workout>
    suspend fun getWorkoutDetails(id: Int): WorkoutDetails
}

class RealWorkoutRepository(private val dataSource: WorkoutDataSource) : WorkoutRepository {

    override suspend fun getWorkouts(): List<Workout> {
        return withContext(Dispatchers.IO) {
            dataSource.getData().map {
                Workout(
                    id = it.id,
                    name = it.name,
                    duration = it.duration,
                    imageUrl = it.imageUrl
                )
            }
        }
    }

    override suspend fun getWorkoutDetails(id: Int): WorkoutDetails {
        val workout = dataSource.getData().find { it.id == id }!!
        return WorkoutDetails(
            id = workout.id,
            name = workout.name,
            duration = workout.duration,
            difficulty = workout.difficulty,
            description = workout.description,
            imageUrl = workout.imageUrl
        )
    }
}
