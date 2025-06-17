package com.example.myweatherapp.presentation.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myweatherapp.domain.repositories.LocationRepository
import com.example.myweatherapp.presentation.viewmodel.LocationViewModel


class LocationViewModelProviderFactory(
    val locationRepository: LocationRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationViewModel(locationRepository ) as T
    }

}