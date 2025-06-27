package com.example.myweatherapp.domain.models.currentweather

data class Location(
    val name: String,
    val country: String,
    val timeZone: String,
    val localTime: String
)
