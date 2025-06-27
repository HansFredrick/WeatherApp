package com.example.myweatherapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.domain.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
   private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            val getWeatherResult= weatherRepository.getCurrentWeather(q = "Manila",aqi = "yes")
            val forecastResponse = weatherRepository.getForecastWeather(q = "Manila",aqi = "yes", alrt = "yes", dy = 10)

            getWeatherResult.onSuccess {
                    _uiState.update { currentState ->
                        currentState.copy(weather = getWeatherResult.getOrNull())
                    }
            }
            getWeatherResult.onFailure {
                //shopw error

            }


//            if (weatherResponse != null) {
//                if(weatherResponse.isSuccessful){
//                    val weatherRemote = weatherResponse.body()
//                    _uiState.update { currentState ->
//                        currentState.copy(weather = weatherRemote)
//                    }
//                }
//            }

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