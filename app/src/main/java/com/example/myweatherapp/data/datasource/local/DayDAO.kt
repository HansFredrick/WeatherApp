package com.example.myweatherapp.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myweatherapp.data.entities.forecastweather.room.DayEntity

@Dao
interface DayDAO {
    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)// onConflict in case of inserting repeated location
    // upsert == update and insert
    // long : the id that was inserted
    suspend fun upsert(day: DayEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)// onConflict in case of inserting repeated location
    suspend fun upsert(entities: List<DayEntity>): List<Long>

    //READ
    @Query("SELECT * FROM dayTable WHERE forecastDayId = :forecastDayId ")
    suspend fun getDayByForecastId(forecastDayId: Int): DayEntity?

    //DELETE
    @Delete
    suspend fun deleteDay(day: DayEntity)
}