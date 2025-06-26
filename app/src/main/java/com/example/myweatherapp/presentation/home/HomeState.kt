package com.example.myweatherapp.presentation.home

import com.example.myweatherapp.data.entities.currentweather.remote.CurrentWeatherResponse
import com.example.myweatherapp.data.entities.forecastweather.remote.ForecastWeatherResponse

data class HomeState (
    val weather : CurrentWeatherResponse?= null,
    val forecast : ForecastWeatherResponse?=null,
    val isLoading: Boolean = true
)