package com.example.myweatherapp.data.entities.currentweather.remote


data class CurrentWeatherResponse(
    val location: WeatherLocationRemote,
    val current : CurrentWeatherRemote
)
