package com.example.myweatherapp.data.entities.forecastweather.remote

import com.google.gson.annotations.SerializedName

class ForecastXRemote (
    @SerializedName("forecastday") val forecastday: List<ForecastDayRemote>
    )
