package com.example.myweatherapp.domain.models.current.roomentities

import androidx.room.Entity
import androidx.room.PrimaryKey

import androidx.room.ForeignKey
import androidx.room.Index
import com.example.myweatherapp.domain.models.current.AirQuality
import com.example.myweatherapp.domain.models.current.Condition

@Entity(
    tableName = "current_weather" ,
    foreignKeys = [ForeignKey(
        entity = Location::class,
        parentColumns = ["location_id"],
        childColumns = ["location_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["location_id"])]
)

data class CurrentX(
    @PrimaryKey(autoGenerate = true)
    var current_id:Int,

    var location_id:Int, // FK to Location.id
    val air_quality: AirQuality,
    val cloud: Int,
    val condition: Condition,
    val dewpoint_c: Double,
    val dewpoint_f: Double,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val gust_kph: Double,
    val gust_mph: Double,
    val heatindex_c: Double,
    val heatindex_f: Double,
    val humidity: Int,
    val is_day: Int,
    val last_updated: String,
    val last_updated_epoch: Int,
    val precip_in: Int,
    val precip_mm: Int,
    val pressure_in: Double,
    val pressure_mb: Int,
    val temp_c: Double,
    val temp_f: Double,
    val uv: Double,
    val vis_km: Int,
    val vis_miles: Int,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_kph: Int,
    val wind_mph: Double,
    val windchill_c: Double,
    val windchill_f: Double
)