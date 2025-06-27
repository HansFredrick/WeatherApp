package com.example.myweatherapp.domain.models.forecast

import com.google.gson.annotations.SerializedName

data class ForecastX (
   val forecastDay: List<ForecastDay>
)
