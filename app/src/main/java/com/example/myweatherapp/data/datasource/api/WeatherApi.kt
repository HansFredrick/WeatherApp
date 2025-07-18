package com.example.myweatherapp.data.datasource.api

import com.example.myweatherapp.data.entities.weather.currentweather.remote.CurrentWeatherResponse
import com.example.myweatherapp.data.entities.weather.forecastweather.remote.ForecastWeatherResponse
import com.example.myweatherapp.util.Constants.Companion.API_KEY_WEATHER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
//    get current weather
    @GET("v1/current.json")
    suspend fun getCurrentWeather(
    @Query("key") apiKey: String = API_KEY_WEATHER,
    @Query("q") location: String,
    @Query("aqi") airQuality: String
    ):Response<CurrentWeatherResponse>

//    get 10-day forecast
    @GET("v1/forecast.json")
    suspend fun getForecastWeather(
    @Query("key") apiKey: String = API_KEY_WEATHER,
    @Query("q") location: String,
    @Query("days") days: Int,
    @Query("aqi") airQuality: String,
    @Query("alerts") alerts: String
    ):Response<ForecastWeatherResponse>
}