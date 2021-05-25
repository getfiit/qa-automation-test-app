package com.example.fiitqatest.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fiitqatest.data.RawResourcesWorkoutDataSource
import com.example.fiitqatest.data.RealWorkoutRepository
import com.example.fiitqatest.ui.details.DetailsViewModel
import com.example.fiitqatest.ui.login.LoginViewModel
import com.example.fiitqatest.ui.main.MainViewModel

class LoginActivityViewModelProvider() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel() as T
    }
}

class MainActivityViewModelProvider(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val dataSource = RawResourcesWorkoutDataSource(context)
        return MainViewModel(RealWorkoutRepository(dataSource)) as T
    }
}

class DetailsActivityViewModelProvider(private val context: Context, private val id: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val dataSource = RawResourcesWorkoutDataSource(context)
        return DetailsViewModel(id, RealWorkoutRepository(dataSource)) as T
    }
}
