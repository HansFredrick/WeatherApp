package com.example.myweatherapp.data.db.forecast

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myweatherapp.domain.model.forecast.Forecastday

interface ForecastDayDAO {
    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)// onConflict in case of inserting repeated location
    // upsert == update and insert
    // long : the id that was inserted
    suspend fun upsert (forecastDay: Forecastday) : Long

    //READ
    @Query("SELECT * FROM forecast_day")
    fun getAllLocations () : LiveData<List<Forecastday>>

    //DELETE
    @Delete
    suspend fun deleteLocation(day: Forecastday)
}