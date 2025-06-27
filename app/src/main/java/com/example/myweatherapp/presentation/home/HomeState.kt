package com.example.myweatherapp.presentation.home

import com.example.myweatherapp.data.entities.currentweather.remote.CurrentWeatherResponse
import com.example.myweatherapp.data.entities.forecastweather.remote.ForecastWeatherResponse
import com.example.myweatherapp.domain.models.currentweather.Weather

data class HomeState (
    val weather : Weather?= null,
    val forecast : ForecastWeatherResponse?=null,
    val isLoading: Boolean = true
)