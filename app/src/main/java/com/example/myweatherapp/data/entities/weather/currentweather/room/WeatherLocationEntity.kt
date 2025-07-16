package com.example.myweatherapp.data.entities.weather.currentweather.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "weatherLocation"
)
data class WeatherLocationEntity(
    @PrimaryKey(autoGenerate = true)
    var locationId:Int? = null,
    val country: String,
    val localtime: String,
    val name: String,
    val timeZone: String,
)