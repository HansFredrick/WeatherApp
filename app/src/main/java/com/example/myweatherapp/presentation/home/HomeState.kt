package com.example.myweatherapp.presentation.home

import com.example.myweatherapp.domain.models.currentweather.Weather
import com.example.myweatherapp.domain.models.forecast.ForecastDay

data class HomeState(
    val weather: Weather? = null,
    val forecastDays: List<ForecastDay>? = emptyList(),
    val isLoading: Boolean = true
)