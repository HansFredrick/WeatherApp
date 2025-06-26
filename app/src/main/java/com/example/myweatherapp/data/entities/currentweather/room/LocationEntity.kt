package com.example.myweatherapp.data.entities.currentweather.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "location"
)
data class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    var locationId:Int,

    val country: String,
    val localtime: String,
    val name: String,
    val timeZone: String
)