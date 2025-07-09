package com.example.myweatherapp.presentation.home

sealed class HomeIntent {

   data object OnLoginClicked:HomeIntent()
    data class OnLocationSearched(val locationName:String):HomeIntent()

}