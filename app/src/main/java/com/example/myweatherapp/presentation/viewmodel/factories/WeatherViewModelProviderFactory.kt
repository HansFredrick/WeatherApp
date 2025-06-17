package com.example.myweatherapp.presentation.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myweatherapp.domain.repositories.WeatherRepository
import com.example.myweatherapp.presentation.viewmodel.WeatherViewModel

class WeatherViewModelProviderFactory(
    val weatherRepository: WeatherRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(weatherRepository ) as T
    }

}