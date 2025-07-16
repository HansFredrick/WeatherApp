package com.example.myweatherapp.domain.models.currentweather

import com.example.myweatherapp.domain.models.forecast.ForecastDay


data class Weather(
    val weatherLocation: WeatherLocation?,
    val current: CurrentWeather?,
    val forecastDay: List<ForecastDay>
)
