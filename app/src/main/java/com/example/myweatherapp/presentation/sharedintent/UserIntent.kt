package com.example.myweatherapp.presentation.sharedintent

sealed class UserIntent {
    data class OnLocationSearched(val locationName: String) : UserIntent()
    data class OnLocationSelected(val locationName: String) : UserIntent()
}