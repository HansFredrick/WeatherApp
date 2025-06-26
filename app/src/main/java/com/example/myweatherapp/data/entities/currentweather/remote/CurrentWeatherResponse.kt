package com.example.myweatherapp.data.entities.currentweather.remote


data class CurrentWeatherResponse(
    val location: LocationRemote,
    val current : CurrentWeatherRemote
)