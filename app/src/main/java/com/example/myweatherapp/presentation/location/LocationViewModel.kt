//package com.example.myweatherapp.presentation.viewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.myweatherapp.domain.repositories.LocationRepository
//import com.example.myweatherapp.presentation.location.LocationState
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//
//class LocationViewModel (
//    val locationRepository : LocationRepository
//): ViewModel() {
//    val _uiState = MutableStateFlow(LocationState())
//
//    init{
//        getData()
//    }
//
//    fun getData(){
//        viewModelScope.launch {
//            val getLocationsResult = locationRepository.getLocations()
//            _uiState.update { currentState ->
//                currentState.copy(
//                    visitedLocation = getLocationsResult
//                )
//
//            }
//        }
//    }
//
//
//}