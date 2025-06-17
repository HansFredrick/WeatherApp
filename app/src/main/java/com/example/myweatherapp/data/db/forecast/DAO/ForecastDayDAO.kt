package com.example.myweatherapp.data.db.forecast.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myweatherapp.domain.model.forecast.Forecastday
import com.example.myweatherapp.domain.model.forecast.wrapper.ForecastWithDayAndHours

@Dao
interface ForecastDayDAO {
    // CREATE / UPSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(forecastDay: Forecastday): Long

    // READ all Forecasts with their related Day and Hour entities
    @Transaction
    @Query("SELECT * FROM forecast_day")
    fun getAllForecastsWithDayAndHours(): LiveData<List<ForecastWithDayAndHours>>

    // DELETE
    @Delete
    suspend fun deleteForecast(forecastDay: Forecastday)
}