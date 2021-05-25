package com.example.fiitqatest.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val LOGIN_DELAY = 2000L
private val CREDENTIALS = mapOf(
    "username@fiit.tv" to "longpassword",
    "login@fiit.tv" to "verystrongpassword"
)

class LoginViewModel : ViewModel() {

    private val _state = MutableStateFlow<ViewState>(ViewState.Idle)
    val state: Flow<ViewState> = _state.asStateFlow()

    fun onLoginClick(email: String, password: String) {
        viewModelScope.launch {
            _state.emit(ViewState.Loading)
            delay(LOGIN_DELAY)
            if (loginCredentialsCorrect(email, password)) {
                _state.emit(ViewState.LoginSuccess)
            } else {
                _state.emit(ViewState.LoginFailed)
            }
        }
    }

    private fun loginCredentialsCorrect(email: String, password: String): Boolean {
        return CREDENTIALS[email].equals(password)
    }

    sealed class ViewState {
        object Idle : ViewState()
        object Loading : ViewState()
        object LoginSuccess : ViewState()
        object LoginFailed : ViewState()
    }
}
