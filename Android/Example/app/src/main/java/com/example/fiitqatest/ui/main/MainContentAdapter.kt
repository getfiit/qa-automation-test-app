package com.example.fiitqatest.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fiitqatest.R
import com.example.fiitqatest.databinding.ItemButtonBinding
import com.example.fiitqatest.databinding.ItemWorkoutBinding
import com.example.fiitqatest.ui.ImageLoader
import com.example.fiitqatest.ui.main.MainContent.LogoutButton
import com.example.fiitqatest.ui.main.MainContent.WorkoutItem

private const val TYPE_WORKOUT = 1
private const val TYPE_LOGOUT_BUTTON = 2

class MainContentAdapter(private val listener: Listener) :
    ListAdapter<MainContent, ViewHolder>(DiffCallback()) {

    inner class WorkoutViewHolder(private val view: ItemWorkoutBinding) : ViewHolder(view.root) {
        fun bind(item: WorkoutItem, onItemClick: (id: Int) -> Unit) {
            view.lessonName.text = item.name
            ImageLoader.loadImage(view.coverImage, item.imageUrl)
            view.root.setOnClickListener { onItemClick(item.id) }
        }
    }

    inner class LogoutButtonViewHolder(private val view: ItemButtonBinding) :
        ViewHolder(view.root) {
        fun bind(onClick: () -> Unit) {
            view.root.setText(R.string.main_button_logout)
            view.root.setOnClickListener { onClick() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_WORKOUT -> {
                val binding =
                    ItemWorkoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                WorkoutViewHolder(binding)
            }
            TYPE_LOGOUT_BUTTON -> {
                val binding =
                    ItemButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                LogoutButtonViewHolder(binding)
            }
            else -> throw IllegalStateException("Unknown item type")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_WORKOUT -> (holder as WorkoutViewHolder).bind(
                item = getItem(position) as WorkoutItem,
                onItemClick = { listener.onWorkoutItemClick(it) }
            )
            TYPE_LOGOUT_BUTTON -> (holder as LogoutButtonViewHolder).bind(
                onClick = { listener.onLogoutClick() }
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is WorkoutItem -> TYPE_WORKOUT
            is LogoutButton -> TYPE_LOGOUT_BUTTON
            else -> throw IllegalStateException("Unknown item type")
        }
    }

    interface Listener {
        fun onWorkoutItemClick(id: Int)
        fun onLogoutClick()
    }
}

class DiffCallback : DiffUtil.ItemCallback<MainContent>() {
    override fun areItemsTheSame(oldItem: MainContent, newItem: MainContent): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MainContent, newItem: MainContent): Boolean {
        return oldItem == newItem
    }
}
