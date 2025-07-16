package com.example.myweatherapp.data.entities.weather.forecastweather.room

import androidx.room.Embedded
import androidx.room.Relation
/*
Technically an entity
serves as  relational class
 */
data class ForecastDayWrapper (
    @Embedded val forecastDayEntity: ForecastDayEntity,
    @Relation (
        parentColumn = "forecastDayId",
        entityColumn = "forecastDayId"
    )val dayEntity: DayEntity?
)

