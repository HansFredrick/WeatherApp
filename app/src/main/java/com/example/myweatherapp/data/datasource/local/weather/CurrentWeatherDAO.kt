package com.example.myweatherapp.data.datasource.local.weather

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myweatherapp.data.entities.weather.currentweather.room.CurrentWeatherEntity
import com.example.myweatherapp.data.entities.weather.currentweather.room.WeatherLocationWrapper
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDAO {
    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)// onConflict in case of inserting repeated location
    // upsert == update and insert
    // long : the id that was inserted
    suspend fun upsert(currentWeather: CurrentWeatherEntity): Long


    @Transaction
    @Query("SELECT * FROM weatherLocation WHERE name = :locationName")
    fun getLiveCurrentWeatherByLocation(locationName: String): Flow<WeatherLocationWrapper?>


    //DELETE
    @Delete
    suspend fun deleteLocation(location: CurrentWeatherEntity)
}