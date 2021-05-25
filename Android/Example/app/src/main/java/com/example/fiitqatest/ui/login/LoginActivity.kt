package com.example.fiitqatest.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.fiitqatest.ui.LoginActivityViewModelProvider
import com.example.fiitqatest.R
import com.example.fiitqatest.databinding.ActivityLoginBinding
import com.example.fiitqatest.ui.login.LoginViewModel.ViewState.*
import com.example.fiitqatest.ui.main.MainActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels { LoginActivityViewModelProvider() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.state
            .onEach {
                when (it) {
                    is Idle -> {
                        with(binding) {
                            setEnabled(true)
                            showLoading(false)
                        }
                    }
                    LoginFailed -> {
                        Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show()
                        with(binding) {
                            clearInputs()
                            setEnabled(true)
                            showLoading(false)
                        }

                    }
                    LoginSuccess -> {
                        startActivity(Intent(this, MainActivity::class.java))
                        with(binding) {
                            clearInputs()
                            setEnabled(true)
                            showLoading(false)
                        }
                    }
                    Loading -> {
                        with(binding) {
                            setEnabled(false)
                            showLoading(true)
                        }
                    }
                }
            }.launchIn(lifecycleScope)

        binding.signInButton.setOnClickListener {
            viewModel.onLoginClick(
                email = binding.emailInput.text.toString(),
                password = binding.passwordInput.text.toString()
            )
        }
    }
}

private fun ActivityLoginBinding.showLoading(isShown: Boolean) {
    signInLoadingSpinner.isVisible = isShown
}

private fun ActivityLoginBinding.setEnabled(enabled: Boolean) {
    passwordInput.isEnabled = enabled
    emailInput.isEnabled = enabled
    signInButton.isEnabled = enabled
}

private fun ActivityLoginBinding.clearInputs() {
    passwordInput.text.clear()
    emailInput.text.clear()
}
