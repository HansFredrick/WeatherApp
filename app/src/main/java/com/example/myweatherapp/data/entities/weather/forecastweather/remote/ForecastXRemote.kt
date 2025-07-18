package com.example.myweatherapp.data.entities.weather.forecastweather.remote

import com.google.gson.annotations.SerializedName

// ForecastXRemote containt the list of forecast days
class ForecastXRemote (
    @SerializedName("forecastday") val forecastDay: List<ForecastDayRemote>
    )
