package com.example.myweatherapp.domain.models.forecast

import com.example.myweatherapp.domain.models.forecast.roomentities.Day
import com.example.myweatherapp.domain.models.forecast.roomentities.Hour

data class ForecastDayDto( // <- This is your API response model
    val date: String,
    val date_epoch: Int,
    val astro: Astro,
    val day: Day,
    val hour: List<Hour>
)