package com.example.myweatherapp.presentation.main

import com.example.myweatherapp.domain.models.location.Location

sealed class MainIntent {
    data class OnLocationSearched(val locationName: String) : MainIntent()
    data class OnLocationSelected(val location: Location) : MainIntent()
}