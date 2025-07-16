package com.example.myweatherapp.data.entities.location.room

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(
    tableName = "location"
)
data class LocationEntity (
    @PrimaryKey
    val placeID: Int ?= null,
    val displayName: String ?= "",
    val latitude: String ?= "",
    val longitude : String ?= ""
)