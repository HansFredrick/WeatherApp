package com.example.myweatherapp.data.entities.forecastweather.room

import androidx.room.Embedded
import androidx.room.Relation
/*
Technically an entity
serves as  relational class
 */
data class ForecastDayWrapper (
    @Embedded val locationInfoEntity: ForecastDayEntity,
    @Relation (
        parentColumn = "forecastDayId",
        entityColumn = "forecastDayId"
    )val currentWeatherEntity: DayEntity?
)

