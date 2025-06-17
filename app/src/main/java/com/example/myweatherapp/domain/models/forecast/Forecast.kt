package com.example.myweatherapp.domain.models.forecast

data class Forecast(
    val alerts: Alerts,
    val current: Current,
    val forecast: ForecastX,
    val location: Location
)