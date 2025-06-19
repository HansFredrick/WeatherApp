package com.example.myweatherapp.data.entities.forecastweather

import com.google.gson.annotations.SerializedName

class DayConditionRemote(
    @SerializedName("text") val text :String,
    @SerializedName("icon") val icon :String
)