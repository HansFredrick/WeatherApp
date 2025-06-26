package com.example.myweatherapp.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myweatherapp.data.entities.currentweather.room.CurrentWeatherEntity

@Dao
interface CurrentWeatherDAO {
    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)// onConflict in case of inserting repeated location
    // upsert == update and insert
    // long : the id that was inserted
    suspend fun upsert (location: CurrentWeatherEntity) : Long

    //READ
    @Query("SELECT * FROM currentWeather")
    fun getAllCurrentWeather () : LiveData<List<CurrentWeatherEntity>>

    @Query("SELECT * FROM currentWeather WHERE locationId = :locationId")
    suspend fun getCurrentWeatherByLocation(locationId: Int): CurrentWeatherEntity?

    //DELETE
    @Delete
    suspend fun deleteLocation(location: CurrentWeatherEntity)
}