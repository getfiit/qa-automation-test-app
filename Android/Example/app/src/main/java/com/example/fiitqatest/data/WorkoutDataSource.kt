package com.example.fiitqatest.data

import android.content.Context
import com.example.fiitqatest.R
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.delay

interface WorkoutDataSource {
    suspend fun getData(): List<WorkoutRoot.WorkoutDTO>
}

private const val FETCH_DELAY = 2000L

class RawResourcesWorkoutDataSource(private val context: Context) : WorkoutDataSource {
    private val jsonAdapter: JsonAdapter<WorkoutRoot> =
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
            .adapter(WorkoutRoot::class.java)

    override suspend fun getData(): List<WorkoutRoot.WorkoutDTO> {

        delay(FETCH_DELAY)
        val text = context.resources.openRawResource(R.raw.content)
            .bufferedReader().use { it.readText() }

        val workouts = jsonAdapter.fromJson(text)
        return workouts?.workouts.orEmpty()
    }
}
