package com.example.fiitqatest.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fiitqatest.databinding.ActivityMainBinding
import com.example.fiitqatest.ui.MainActivityViewModelProvider
import com.example.fiitqatest.ui.details.DetailsActivity
import com.example.fiitqatest.ui.login.LoginActivity
import com.example.fiitqatest.ui.main.MainViewModel.ViewState.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity(), MainContentAdapter.Listener {

    private val viewModel: MainViewModel by viewModels { MainActivityViewModelProvider(this.applicationContext) }
    private val contentAdapter = MainContentAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.content) {
            adapter = contentAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        }

        viewModel.state
            .onEach {
                when (it) {
                    is Content -> {
                        binding.mainLoadingSpinner.isVisible = false
                        contentAdapter.submitList(it.content)
                    }
                    is Loading -> {
                        binding.mainLoadingSpinner.isVisible = true
                    }
                    is Idle -> {
                        binding.mainLoadingSpinner.isVisible = false
                    }
                }

            }.launchIn(lifecycleScope)

        viewModel.startLoadingContent()
    }

    override fun onWorkoutItemClick(id: Int) {
        startActivity(DetailsActivity.createIntent(this, id))
    }

    override fun onLogoutClick() {
        startActivity(
            Intent(
                this,
                LoginActivity::class.java
            ).apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK); })
    }
}
