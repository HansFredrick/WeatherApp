package com.example.myweatherapp.data.datasource

import com.example.myweatherapp.data.entities.location.remote.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET("/search?")
    suspend fun findLocation(
        @Query("q") locationName: String,
        @Query("format") format: String
    ):LocationResponse
}