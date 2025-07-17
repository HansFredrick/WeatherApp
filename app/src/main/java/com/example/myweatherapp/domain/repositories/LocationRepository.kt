package com.example.myweatherapp.domain.repositories

import com.example.myweatherapp.data.datasource.api.LocationApi
import com.example.myweatherapp.data.datasource.local.location.LocationDAO
import com.example.myweatherapp.data.mappers.toDomain
import com.example.myweatherapp.data.mappers.toEntity
import com.example.myweatherapp.domain.models.location.Location
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationApi: LocationApi,
    private val locationDAO: LocationDAO
) {
    fun getVisitedLocations():List<Location>{
        var visitedLocations= emptyList<Location>()
        locationDAO.getAllLocations().map {
                it.map {
                    visitedLocations = listOf(it.toDomain())
                }
        }
        return visitedLocations
    }

    suspend fun searchLocation (q:String):List<Location>{
        val locationResult = locationApi.searchLocation(location = q).locations.map{
            it.toDomain()
        }
        return locationResult
    }

    suspend fun upsertLocation(location:Location){
        locationDAO.saveEntity(location = location.toEntity())
    }

}
