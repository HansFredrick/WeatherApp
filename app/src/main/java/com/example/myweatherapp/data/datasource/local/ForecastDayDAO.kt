package com.example.myweatherapp.data.datasource.local
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myweatherapp.data.entities.forecastweather.room.ForecastDayEntity

@Dao
interface ForecastDayDAO {
    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)// onConflict in case of inserting repeated location
    // upsert == update and insert
    // long : the id that was inserted
    suspend fun upsert (forecastDay: ForecastDayEntity) : Long

    //READ
    @Transaction
    @Query("SELECT * FROM forecastDay WHERE locationId = :locationId")
    suspend fun getForecastWithDay(locationId: Int): List<ForecastDayEntity>

    //DELETE
    @Delete
    suspend fun deleteForecastDay(forecastDay: ForecastDayEntity)
}