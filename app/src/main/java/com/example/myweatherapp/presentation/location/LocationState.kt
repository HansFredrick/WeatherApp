package com.example.myweatherapp.presentation.location

import com.example.myweatherapp.domain.models.location.Location

data class LocationState (
    val visitedLocation: List<Location> ?= emptyList(),
    val choicesLocation: List<Location> ?= emptyList(),
    val currentLocation: String ?= null,
    val isLoading: Boolean = true
)