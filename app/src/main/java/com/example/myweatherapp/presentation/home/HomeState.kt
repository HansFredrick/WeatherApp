package com.example.myweatherapp.presentation.home

import com.example.myweatherapp.data.entities.currentweather.CurrentWeatherResponse
import com.example.myweatherapp.data.entities.forecastweather.ForecastWeatherResponse

data class HomeState (
    val weather : CurrentWeatherResponse?= null,
    val forecast : ForecastWeatherResponse?=null
)