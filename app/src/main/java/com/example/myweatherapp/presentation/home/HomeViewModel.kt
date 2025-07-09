package com.example.myweatherapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.domain.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    val _uiState = MutableStateFlow(HomeState())

    init {
        getData()
    }

    fun getData() {

        viewModelScope.launch {
            val getWeatherResult = weatherRepository.getCurrentWeather(
                q = "Manila",
                aqi = "yes",
                dy = 10,
                alrt = "yes"
            )
            getWeatherResult.collectLatest { result ->
                println("Location View model fetching: " + result.message)
                _uiState.update { currentState ->
                    currentState.copy(
                        weather = result.data,
                        isLoading = false,
                        forecastDays = result.data?.forecastDay
                    )
                }
                //println("UI location id:"+result.data?.location?.id)
            }
        }
    }

    fun receiveSelectedLocation(location: String){

    }

//    fun setIntent(intent: HomeIntent) {
//        when (intent) {
//            HomeIntent.OnLoginClicked -> {
//
//            }
//
//            is HomeIntent.OnLocationSearched -> {
//
//            }
//            is HomeIntent.OnLocationSelected -> {
//
//            }
//        }
//    }



}