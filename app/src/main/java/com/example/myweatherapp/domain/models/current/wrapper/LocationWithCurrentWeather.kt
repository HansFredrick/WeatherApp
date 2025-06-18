package com.example.myweatherapp.domain.models.current.wrapper

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myweatherapp.domain.models.current.roomentities.CurrentX
import com.example.myweatherapp.domain.models.current.roomentities.Location

data class LocationWithCurrentWeather (
    @Embedded val location: Location,
    @Relation(
        parentColumn = "location_id",
        entityColumn = "location_id",
        entity = CurrentX::class
    )
    val current: CurrentX
)