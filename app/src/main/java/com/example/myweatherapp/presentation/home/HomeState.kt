package com.example.myweatherapp.presentation.home

import com.example.myweatherapp.data.entities.CurrentWeatherResponse
import com.example.myweatherapp.data.entities.LocationRemote

data class HomeState (
    val weather :CurrentWeatherResponse?= null
)