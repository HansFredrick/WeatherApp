package com.example.myweatherapp.data.db.forecast.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myweatherapp.domain.models.forecast.roomentities.Day
import com.example.myweatherapp.domain.models.forecast.roomentities.Forecastday
import com.example.myweatherapp.domain.models.forecast.roomentities.Hour
import com.example.myweatherapp.domain.models.forecast.wrapper.ForecastWithDayAndHours

/**
 * This dao is used to store the compilation of days, forecastdays and hours
 */
@Dao
interface ForecastDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecastDays(forecastDays: List<Forecastday>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDays(days: List<Day>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHours(hours: List<Hour>)

    // --- Clear Old Data (optional) ---

    @Query("DELETE FROM forecast_day")
    suspend fun clearForecastDays()

    @Query("DELETE FROM day")
    suspend fun clearDays()

    @Query("DELETE FROM hours")
    suspend fun clearHours()

    // --- Query for UI Wrapper ---

    @Transaction
    @Query("SELECT * FROM forecast_day")
    suspend fun getForecastWithDayAndHours(): List<ForecastWithDayAndHours>
}