package com.example.myweatherapp.data.entities.forecastweather.remote

import com.google.gson.annotations.SerializedName

// ForecastXRemote containt the list of forecast days
class ForecastXRemote (
    @SerializedName("forecastday") val forecastday: List<ForecastDayRemote>
    )
