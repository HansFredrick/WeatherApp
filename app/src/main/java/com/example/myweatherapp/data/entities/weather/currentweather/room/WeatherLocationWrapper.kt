package com.example.myweatherapp.data.entities.weather.currentweather.room

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myweatherapp.data.entities.weather.forecastweather.room.ForecastDayWrapper
import com.example.myweatherapp.data.entities.weather.forecastweather.room.ForecastDayEntity

data class WeatherLocationWrapper (
    @Embedded val weatherLocationEntity: WeatherLocationEntity,

    @Relation (
        parentColumn = "locationId",
        entityColumn = "locationId"
    )val currentWeatherEntity: CurrentWeatherEntity?,

    @Relation(
        entity = ForecastDayEntity::class,
        parentColumn = "locationId",
        entityColumn = "locationId"
    )val forecastDayWrapper: List<ForecastDayWrapper>?
)

