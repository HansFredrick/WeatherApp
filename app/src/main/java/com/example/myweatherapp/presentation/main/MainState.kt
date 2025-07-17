package com.example.myweatherapp.presentation.main

import com.example.myweatherapp.domain.models.currentweather.Weather
import com.example.myweatherapp.domain.models.forecast.ForecastDay
import com.example.myweatherapp.domain.models.location.Location

data class MainState(
    val weather: Weather? = null,
    val forecastDays: List<ForecastDay>? = emptyList(),
    val visitedLocation: List<Location> ?= emptyList(),
    val choicesLocation: List<Location> ?= emptyList(),
    val currentLocation: String = "14.5904,120.9803",
    val isLoading: Boolean = true
)