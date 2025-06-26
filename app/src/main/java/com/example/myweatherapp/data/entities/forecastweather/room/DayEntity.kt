package com.example.myweatherapp.data.entities.forecastweather.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "dayTable",
    foreignKeys =  [ForeignKey(
        entity = ForecastDayEntity::class,
        parentColumns = ["forecastDayId"],
        childColumns = ["forecastDayId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["forecastDayId"])]
)
data class DayEntity(
    @PrimaryKey(autoGenerate = true)
    var dayId:Int,

    var forecastDayId:Int,
    val averageTemperatureCelsius : Double,
    val maximumTemperatureCelsius : Double,
    val dayConditionText : String,
    val dayConditionIcon : String,
)