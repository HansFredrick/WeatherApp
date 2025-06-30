package com.example.myweatherapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.domain.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.lastOrNull
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
            val getWeatherResult = weatherRepository.getCurrentWeather(q = "Manila", aqi = "yes")
            getWeatherResult.collectLatest { result ->
                _uiState.update { currentState ->
                    currentState.copy(weather = result.data, isLoading = false)
                    

                }
            }
            getWeatherResult.lastOrNull()?.data?.location?.id?.let { getForecast(it) }



        }
    }
    fun getForecast(locationID:Int){
        viewModelScope.launch {
            val getForecastResult = weatherRepository.getForecastWeather(
                q = "Manila", aqi = "yes", alrt = "yes", locationID = locationID,
                dy = 10
            )
            getForecastResult.collectLatest { result ->
                _uiState.update { currentState ->
                    currentState.copy(forecast = result.data, isLoading = false)


                }
            }
        }
    }


}