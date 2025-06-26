package com.example.myweatherapp.domain.repositories

import com.example.myweatherapp.data.datasource.local.LocationDAO
import javax.inject.Inject

class LocationRepository @Inject constructor(
    val locationDAO: LocationDAO
) {

}