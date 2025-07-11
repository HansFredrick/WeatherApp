package com.example.myweatherapp.data.entities.currentweather.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "location"
)
data class WeatherLocationEntity(
    @PrimaryKey(autoGenerate = true)
    var locationId:Int? = null,
    val country: String,
    val localtime: String,
    val name: String,
    val timeZone: String,
)