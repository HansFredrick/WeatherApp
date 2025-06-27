package com.example.myweatherapp.domain.models.currentweather

import com.google.gson.annotations.SerializedName

data class CurrentWeather (
    val temperatureCelsius: Double,
    val currentWeatherCondition: CurrentWeatherCondition,
    val airQuality: CurrentWeatherAirQuality,
    val windSpeedMiles: Double,
    val windSpeedKilometers: Double,
    val precipitationMilimeter: Double,
    val precipitationInch: Double,
    val uvIndex : Double

)
