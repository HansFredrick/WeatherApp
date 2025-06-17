package com.example.myweatherapp.domain.model.forecast.wrapper

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myweatherapp.domain.model.forecast.Day
import com.example.myweatherapp.domain.model.forecast.Forecastday
import com.example.myweatherapp.domain.model.forecast.Hour

data class ForecastWithDayAndHours(
    @Embedded
    val forecastday: Forecastday,

    @Relation(
        parentColumn = "forecast_day_id",
        entityColumn = "forecast_day_id",
        entity = Day::class
    )
    val day: Day?,

    @Relation(
        parentColumn = "forecast_day_id",
        entityColumn = "forecast_day_id",
        entity = Hour::class
    )
    val hours: List<Hour>
)