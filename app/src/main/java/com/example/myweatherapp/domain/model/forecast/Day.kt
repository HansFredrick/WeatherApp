package com.example.myweatherapp.domain.model.forecast

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "day",
    foreignKeys = [ForeignKey(
        entity = Forecastday::class,
        parentColumns = ["forecast_day_id"],
        childColumns = ["forecast_day_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Day(
    @PrimaryKey(autoGenerate = true)
    val day_id: Int = 0,
    val forecast_day_id: Int,
    val air_quality: AirQualityX,
    val avghumidity: Int,
    val avgtemp_c: Double,
    val avgtemp_f: Double,
    val avgvis_km: Double,
    val avgvis_miles: Double,
    val condition: Condition,
    val daily_chance_of_rain: Int,
    val daily_chance_of_snow: Int,
    val daily_will_it_rain: Int,
    val daily_will_it_snow: Int,
    val maxtemp_c: Double,
    val maxtemp_f: Double,
    val maxwind_kph: Double,
    val maxwind_mph: Double,
    val mintemp_c: Double,
    val mintemp_f: Double,
    val totalprecip_in: Double,
    val totalprecip_mm: Double,
    val totalsnow_cm: Double,
    val uv: Double
)