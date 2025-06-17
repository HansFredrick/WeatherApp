package com.example.myweatherapp.domain.model.forecast

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "forecast_day"
)
data class Forecastday(
    @PrimaryKey(autoGenerate = true)
    var forecast_day_id:Int,
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
//    val day: Day,
//    val hour: List<Hour>
)