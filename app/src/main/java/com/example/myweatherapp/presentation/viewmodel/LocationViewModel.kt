package com.example.myweatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myweatherapp.domain.repositories.LocationRepository
import com.example.myweatherapp.domain.repositories.WeatherRepository

class LocationViewModel (
    val locationRepository : LocationRepository
): ViewModel() {
}