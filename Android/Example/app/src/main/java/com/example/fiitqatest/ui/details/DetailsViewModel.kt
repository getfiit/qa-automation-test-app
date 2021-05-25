package com.example.fiitqatest.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiitqatest.R
import com.example.fiitqatest.data.WorkoutRepository
import com.example.fiitqatest.domain.WorkoutDetails
import com.example.fiitqatest.ui.details.DetailsContent.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val id: Int, private val repo: WorkoutRepository) : ViewModel() {
    private val _state = MutableStateFlow<ViewState>(ViewState.Idle)
    val state: Flow<ViewState> = _state.asStateFlow()

    fun startLoadingContent() {
        viewModelScope.launch {
            _state.emit(ViewState.Loading)
            _state.emit(ViewState.Content(repo.getWorkoutDetails(id).toDetailsContent()))
        }
    }

    sealed class ViewState {
        object Idle : ViewState()
        object Loading : ViewState()
        data class Content(val content: List<DetailsContent>) : ViewState()
    }
}

private fun WorkoutDetails.toDetailsContent(): List<DetailsContent> {
    return listOf(
        Hero(name, imageUrl),
        Attributes(listOf(duration, difficulty)),
        Heading(R.string.workout_details_heading),
        Description(description)
    )
}
