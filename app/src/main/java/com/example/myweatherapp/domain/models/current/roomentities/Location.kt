package com.example.myweatherapp.domain.models.current.roomentities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "locations"
)
data class Location(
    @PrimaryKey(autoGenerate = true)
    var location_id:Int,
    val country: String,
    val lat: Double,
    val localtime: String,
    val localtime_epoch: Int,
    val lon: Double,
    val name: String,
    val region: String,
    val tz_id: String
)