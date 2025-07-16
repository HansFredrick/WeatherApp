package com.example.myweatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.domain.repositories.LocationRepository
import com.example.myweatherapp.presentation.sharedintent.IntentBus
import com.example.myweatherapp.presentation.sharedintent.UserIntent
import com.example.myweatherapp.presentation.location.LocationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor (
    val intentBus : IntentBus,
    val locationRepository : LocationRepository
): ViewModel() {
    val _uiState = MutableStateFlow(LocationState())

    init{
        getData()
    }

    fun getData(){
        viewModelScope.launch {
            val getLocationsResult = locationRepository.getLocations()
            _uiState.update { currentState ->
                currentState.copy(
                    visitedLocation = getLocationsResult
                )

            }
        }
    }

    fun sendIntent(intent: UserIntent) = viewModelScope.launch {
        when (intent) {
            is UserIntent.OnLocationSelected -> intentBus.dispatch(intent)
            is UserIntent.OnLocationSearched -> {
                val getLocationsResult = locationRepository.searchLocation(
                    q = intent.locationName
                )
                _uiState.update { currentState ->
                    currentState.copy(
                        choicesLocation = getLocationsResult
                    )

                }
            }
        }
    }

}


//    fun searchLocation(input : String){
//        viewModelScope.launch {
//            val getLocationsResult = locationRepository.searchLocation(
//                q = input
//            )
//            _uiState.update { currentState ->
//                currentState.copy(
//                    choicesLocation = getLocationsResult
//                )
//
//            }
//        }
//
//    }