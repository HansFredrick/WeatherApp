package com.example.myweatherapp.data.entities.currentweather.room

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myweatherapp.data.entities.forecastweather.room.ForecastDayWrapper
import com.example.myweatherapp.data.entities.forecastweather.room.ForecastDayEntity

data class LocationWrapper (
    @Embedded val locationEntity: LocationEntity,

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

