package com.example.myweatherapp.data.entities.forecastweather

import com.google.gson.annotations.SerializedName

class ForecastDayRemote (
    @SerializedName("date") val date : String,
    @SerializedName("day") val day : DayRemote
)