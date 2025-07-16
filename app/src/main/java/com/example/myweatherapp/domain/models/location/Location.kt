package com.example.myweatherapp.domain.models.location

data class Location (
    val placeId: Int ?= null,
    val displayName: String ?= null,
    val longitude: String?= null,
    val latitude: String?= null,
)
