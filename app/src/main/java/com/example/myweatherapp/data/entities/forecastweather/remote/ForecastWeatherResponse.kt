package com.example.myweatherapp.data.entities.forecastweather.remote

import com.google.gson.annotations.SerializedName


class ForecastWeatherResponse (
    @SerializedName("forecast") val forecast : ForecastXRemote
)