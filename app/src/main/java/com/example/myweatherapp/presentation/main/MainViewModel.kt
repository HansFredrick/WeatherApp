package com.example.myweatherapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.domain.repositories.LocationRepository
import com.example.myweatherapp.domain.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    val locationRepository : LocationRepository
) : ViewModel() {

    val _uiState = MutableStateFlow(MainState())

    init {
        getHomeData()
    }

    fun getHomeData(location:String = _uiState.value.currentLocation) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val getWeatherResult = weatherRepository.getCurrentWeather(
                q = location,
                aqi = "yes",
                dy = 10,
                alrt = "yes"
            )
            getWeatherResult.collectLatest { result ->
                println("Location View model fetching: " + result.data?.weatherLocation?.name)
                _uiState.update { currentState ->
                    currentState.copy(
                        weather = result.data,
                        isLoading = false,
                        forecastDays = result.data?.forecastDay
                    )
                }
            }
        }

    }

    fun getLocationData(){
        viewModelScope.launch {
            val getLocationsResult = locationRepository.getVisitedLocations()
            _uiState.update { currentState ->
                currentState.copy(
                    visitedLocation = getLocationsResult
                )

            }

        }
    }


    fun setIntent(intent: MainIntent){
        viewModelScope.launch {
            when(intent){
                is MainIntent.OnLocationSearched -> {
                    val getLocationsResult = locationRepository.searchLocation( q = intent.locationName)
                    _uiState.update { currentState ->
                        currentState.copy(
                            choicesLocation = getLocationsResult
                        )

                    }
                }
                is MainIntent.OnLocationSelected -> {
                    val longitude = "%.4f".format(intent.location.longitude).toDouble()
                    val latitude =  "%.4f".format(intent.location.latitude).toDouble()

                    getHomeData("$longitude , $latitude")
                }
            }
        }


    }






}
/*
 fun collectIntent() { //NOTE: values should me Longitude Latitude
        viewModelScope.launch {
            intentBus.collect { intent ->
                if (intent is MainIntent.OnLocationSelected) {
                    getHomeData(intent.locationName)
                }
            }
        }
    }
 */
