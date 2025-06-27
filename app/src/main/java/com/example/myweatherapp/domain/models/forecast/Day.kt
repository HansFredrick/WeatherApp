package com.example.myweatherapp.domain.models.forecast

data class Day (
    val averageTemperatureCelsus : Double,
    val maximumTemperatureCelsus : Double,
    val dayCondition : DayCondition,
)