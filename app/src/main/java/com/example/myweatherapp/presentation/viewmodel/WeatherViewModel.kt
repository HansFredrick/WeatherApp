package com.example.myweatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myweatherapp.domain.repositories.WeatherRepository

class WeatherViewModel(
    val weatherRepository : WeatherRepository
): ViewModel() {
}