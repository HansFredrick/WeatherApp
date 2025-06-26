package com.example.myweatherapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.domain.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel(
   private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            val weatherResponse= weatherRepository.getCurrentWeather(q = "Manila",aqi = "yes")
            val forecastResponse = weatherRepository.getForecastWeather(q = "Manila",aqi = "yes", alrt = "yes", dy = 10)

            if (weatherResponse != null) {
                if(weatherResponse.isSuccessful){
                    val weatherRemote = weatherResponse.body()
                    _uiState.update { currentState ->
                        currentState.copy(weather = weatherRemote)
                    }
                }
            }

            if(forecastResponse.isSuccessful){
                val forecastRemote = forecastResponse.body()
                _uiState.update { currentState ->
                    currentState.copy(forecast = forecastRemote)
                }
            }

            // updates isLoading
            _uiState.update { currentState ->
                currentState.copy(isLoading = false)
            }

        }
    }


}