package com.example.myweatherapp.data.entities.weather.forecastweather.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.myweatherapp.data.entities.weather.currentweather.room.WeatherLocationEntity

@Entity(
    tableName = "forecastDay",
    foreignKeys = [ForeignKey(
        entity = WeatherLocationEntity::class,
        parentColumns = ["locationId"],
        childColumns = ["locationId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["locationId"])]
)
data class ForecastDayEntity (
    @PrimaryKey(autoGenerate = true)
    val forecastDayId :Int? = null,
    val locationId:Int,
    val date : String
)