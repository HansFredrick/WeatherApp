package com.example.myweatherapp.data.api

import com.example.myweatherapp.domain.model.current.Current
import com.example.myweatherapp.domain.model.forecast.Forecast
import com.example.myweatherapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
//    get current weather
    @GET("v1/current.json")
    suspend fun getCurrentWeather(
    @Query("key") apiKey: String = API_KEY,
    @Query("q") location: String,
    @Query("aqi") airQuality: String
    ):Response<Current>
//    get 10-day forecast

    @GET("v1/forecast.json")
    suspend fun getForecastWeather(
        @Query("key") apiKey: String = API_KEY,
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") airQuality: String,
        @Query("alerts") alerts: String
    ):Response<Forecast>
}