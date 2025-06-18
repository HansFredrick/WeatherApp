package com.example.myweatherapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val weatherRepository = WeatherRepository()
    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()
    init {
        getData()
    }
    private fun getData() {
        viewModelScope.launch {
            val weatherResponse= weatherRepository.getCurrentWeather(q = "Baguio",aqi = "yes")
            if(weatherResponse.isSuccessful){
                val weatherRemote = weatherResponse.body()
                _uiState.update { currentState ->
                    currentState.copy(weather = weatherRemote)


                }
            }

        }
    }


}