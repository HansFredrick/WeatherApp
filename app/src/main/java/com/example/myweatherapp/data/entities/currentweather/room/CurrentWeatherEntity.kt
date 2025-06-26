package com.example.myweatherapp.data.entities.currentweather.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "currentWeather",
    foreignKeys = [ForeignKey(
        entity = LocationEntity::class,
        parentColumns = ["locationId"],
        childColumns = ["locationId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["locationId"])]
)

data class CurrentWeatherEntity (
    @PrimaryKey(autoGenerate = true)
    var currentWeatherId:Int,

    var locationId:Int,
    val temperatureCelsius: Double,
    val currentWeatherConditionText: String,
    val currentWeatherConditionIcon: String,
    val airQuality: Int,
    val windSpeedMiles: Double,
    val windSpeedKilometers: Double,
    val precipitationMilimeter: Double,
    val precipitationInch: Double,
    val uvIndex : Double
)