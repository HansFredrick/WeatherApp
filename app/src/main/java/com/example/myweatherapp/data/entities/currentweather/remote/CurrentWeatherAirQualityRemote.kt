package com.example.myweatherapp.data.entities.currentweather.remote

import com.google.gson.annotations.SerializedName

class CurrentWeatherAirQualityRemote(
    // note values are from 1 to 6
    // use this link:
    // https://chatgpt.com/share/68529c66-f520-8003-b4bb-d48a22452aeb
    @SerializedName("us-epa-index") val  usEPAAirQualityIndex: Int
)