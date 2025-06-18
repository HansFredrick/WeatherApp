package com.example.myweatherapp.domain.models.current

import com.example.myweatherapp.domain.models.current.roomentities.CurrentX
import com.example.myweatherapp.domain.models.current.roomentities.Location

data class Current(
    val current: CurrentX,
    val location: Location
)