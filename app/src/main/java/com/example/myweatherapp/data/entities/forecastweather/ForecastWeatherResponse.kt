package com.example.myweatherapp.data.entities.forecastweather

import com.google.gson.annotations.SerializedName


data class ForecastWeatherResponse (
    @SerializedName("forecast")val forecast : ForecastXRemote
)