package com.example.myweatherapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.domain.repositories.WeatherRepository
import com.example.myweatherapp.presentation.sharedintent.IntentBus
import com.example.myweatherapp.presentation.sharedintent.UserIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val intentBus : IntentBus,
    private val weatherRepository: WeatherRepository,
//    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val _uiState = MutableStateFlow(HomeState())

    init {
        getData()
        collectIntent()
    }

    fun getData( location:String = "Manila" ) {
//        _uiState.update { it.copy(isLoading = true) }

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

    fun collectIntent() {
        viewModelScope.launch {
            intentBus.collect { intent ->
                when (intent) {
                    is UserIntent.OnLocationSelected -> getData(location = intent.locationName)
                    else -> {/* ignore other intents */}
                }
            }
        }
    }


}
