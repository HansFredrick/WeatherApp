package com.example.myweatherapp.data.entities.forecastweather.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.myweatherapp.data.entities.currentweather.room.LocationEntity

@Entity(
    tableName = "forecastDay",
    foreignKeys = [ForeignKey(
        entity = LocationEntity::class,
        parentColumns = ["locationId"],
        childColumns = ["locationId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["locationId"])]
)
data class ForecastDayEntity (
    @PrimaryKey(autoGenerate = true)
    val forecastDayId : Int,
    val locationId:Int,
    val date : String
)