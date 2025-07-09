//package com.example.myweatherapp.domain.repositories
//
//import com.example.myweatherapp.data.datasource.LocationApi
//import com.example.myweatherapp.data.datasource.local.LocationDAO
//import com.example.myweatherapp.data.mappers.toDomain
//import com.example.myweatherapp.domain.models.currentweather.WeatherLocation
//import kotlinx.coroutines.flow.map
//import javax.inject.Inject
//
//class LocationRepository @Inject constructor(
//    private val locationApi: LocationApi,
//    private val locationDAO: LocationDAO
//) {
//    /*
//
//     */
//    fun getLocations():List<WeatherLocation>{
//        val visitedLocations = locationDAO.getAllLocations().map {
//                it.toDomain()
//        }
//        return visitedLocations
//    }
//
//}