package com.example.myweatherapp.data.entities.forecastweather

import com.google.gson.annotations.SerializedName

class DayRemote (
    @SerializedName(" avgtemp_c")val averageTemperatureCelsus : Double,
    @SerializedName("maxtemp_c")val maximumTemperatureCelsus : Double,
    @SerializedName("condition")val dayCondition : DayConditionRemote,
)