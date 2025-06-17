package com.example.myweatherapp.data.db.forecast.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myweatherapp.domain.models.forecast.Day

@Dao
interface DayDAO {
    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)// onConflict in case of inserting repeated location
    // upsert == update and insert
    // long : the id that was inserted
    suspend fun upsert (day: Day) : Long

    //READ
    @Query("SELECT * FROM day")
    fun getAllLocations () : LiveData<List<Day>>

    //DELETE
    @Delete
    suspend fun deleteLocation(day: Day)
}