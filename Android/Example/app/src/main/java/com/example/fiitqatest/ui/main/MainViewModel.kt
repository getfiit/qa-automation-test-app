package com.example.fiitqatest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiitqatest.data.WorkoutRepository
import com.example.fiitqatest.domain.Workout
import com.example.fiitqatest.ui.main.MainViewModel.ViewState.Loading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repo: WorkoutRepository) : ViewModel() {
    private val _state = MutableStateFlow<ViewState>(ViewState.Idle)
    val state: Flow<ViewState> = _state.asStateFlow()

    fun startLoadingContent() {
        viewModelScope.launch {
            _state.emit(Loading)
            _state.emit(ViewState.Content(repo.getWorkouts().toWorkoutItems()))
        }
    }

    sealed class ViewState {
        object Idle : ViewState()
        object Loading : ViewState()
        data class Content(val content: List<MainContent>) : ViewState()
    }
}

private fun List<Workout>.toWorkoutItems(): List<MainContent> {
    return map {
        MainContent.WorkoutItem(it.id, it.name, it.imageUrl, it.duration)
    }.toMutableList<MainContent>().apply {
        add(MainContent.LogoutButton)
    }
}
