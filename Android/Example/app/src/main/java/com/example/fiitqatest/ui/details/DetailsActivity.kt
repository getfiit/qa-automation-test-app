package com.example.fiitqatest.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fiitqatest.databinding.ActivityDetailsBinding
import com.example.fiitqatest.ui.DetailsActivityViewModelProvider
import com.example.fiitqatest.ui.details.DetailsViewModel.ViewState.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DetailsActivity : AppCompatActivity() {
    companion object {
        private const val WORKOUT_ID = "WORKOUT_ID"
        fun createIntent(context: Context, id: Int): Intent {
            return Intent(context, DetailsActivity::class.java).also {
                it.putExtra(WORKOUT_ID, id)
            }
        }
    }

    private val viewModel: DetailsViewModel by viewModels {
        DetailsActivityViewModelProvider(
            this.applicationContext,
            intent.getIntExtra(WORKOUT_ID, -1)
        )
    }
    private val contentAdapter = DetailsContentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.content) {
            adapter = contentAdapter
            layoutManager = LinearLayoutManager(this@DetailsActivity, RecyclerView.VERTICAL, false)
        }

        viewModel.state
            .onEach {
                when (it) {
                    is Content -> {
                        binding.detailsLoadingSpinner.isVisible = false
                        contentAdapter.submitList(it.content)
                    }
                    is Loading -> {
                        binding.detailsLoadingSpinner.isVisible = true
                    }
                    is Idle -> {
                        binding.detailsLoadingSpinner.isVisible = false
                    }
                }

            }.launchIn(lifecycleScope)

        viewModel.startLoadingContent()
    }
}
