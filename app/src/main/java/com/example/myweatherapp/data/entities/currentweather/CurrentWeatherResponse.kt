package com.example.myweatherapp.data.entities.currentweather


data class CurrentWeatherResponse(
    val location: LocationRemote,
    val current : CurrentWeatherRemote
)