package com.example.myweatherapp.data.entities.forecastweather

import com.google.gson.annotations.SerializedName

class ForecastXRemote (
    @SerializedName("forecastday")val forecastday: List<ForecastDayRemote>
    )
