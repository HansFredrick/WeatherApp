package com.example.myweatherapp.domain.repositories

import com.example.myweatherapp.data.db.current.database.CurrentWeatherDatabase
import com.example.myweatherapp.data.db.current.database.LocationDatabase
import com.example.myweatherapp.data.db.forecast.database.DayDatabase
import com.example.myweatherapp.data.db.forecast.database.ForecastDayDatabase
import com.example.myweatherapp.data.db.forecast.database.HourDatabase

class WeatherRepository(
    val currentWeatherDb :CurrentWeatherDatabase,
    val dayDb : DayDatabase,
    val forecstDayDb: ForecastDayDatabase,
    val hourDb: HourDatabase
) {
}