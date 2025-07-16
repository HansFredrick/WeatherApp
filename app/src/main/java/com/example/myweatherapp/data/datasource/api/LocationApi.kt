package com.example.myweatherapp.data.datasource.api

import com.example.myweatherapp.data.entities.location.remote.LocationResponse
import com.example.myweatherapp.util.Constants.Companion.API_KEY_LOCATION
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET("/v1/autocomplete?")
    suspend fun searchLocation(
        @Query("key") locationName:String = API_KEY_LOCATION,
        @Query("q") location: String
    ):LocationResponse
}

