package com.example.uid_project.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.uid_project.data.api.ApiService
import com.example.uid_project.data.api.PlanetApi
import com.example.uid_project.data.repository.MainRepository
import com.example.uid_project.data.repository.ResidentRepository
import com.example.uid_project.ui.main.viewmodel.MainViewModel
import com.example.uid_project.ui.main.viewmodel.ResidentsViewModel

class ViewModelFactory(private val apiService: ApiService): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(apiService.planetApi)) as T
        }

        if (modelClass.isAssignableFrom(ResidentsViewModel::class.java)){
            return ResidentsViewModel(ResidentRepository(apiService.residentApi)) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}