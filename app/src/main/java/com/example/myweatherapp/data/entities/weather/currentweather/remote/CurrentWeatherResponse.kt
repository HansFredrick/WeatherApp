package com.example.myweatherapp.data.entities.weather.currentweather.remote


data class CurrentWeatherResponse(
    val location: WeatherLocationRemote,
    val current : CurrentWeatherRemote
)
