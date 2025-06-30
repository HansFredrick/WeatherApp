package com.example.myweatherapp.presentation.home

import com.example.myweatherapp.domain.models.currentweather.Weather
import com.example.myweatherapp.domain.models.forecast.ForecastWeather

data class HomeState (
    val weather : Weather?= null,
    val forecast : ForecastWeather?=null,
    val isLoading: Boolean = true
)