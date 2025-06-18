package com.example.myweatherapp.data.entities

import com.google.gson.annotations.SerializedName

class CurrentWeatherRemote (
    @SerializedName("temp_c" )val temperatureCelsius: Double,
    @SerializedName("condition" )val currentWeatherCondition: CurrentWeatherConditionRemote,
    @SerializedName("air_quality" ) val airQuality: CurrentWeatherAirQualityRemote,
    @SerializedName("wind_mph") val windSpeedMiles: Double,
    @SerializedName("wind_kph")val windSpedeKilometers: Double,
    @SerializedName("precip_mm")val precipitationMilimeter: Double,
    @SerializedName("precip_in")val precipitationInch: Double,
    @SerializedName("uv") val uvIndex : Double




)