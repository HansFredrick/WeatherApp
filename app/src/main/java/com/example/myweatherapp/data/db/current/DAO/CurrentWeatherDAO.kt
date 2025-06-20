package com.example.myweatherapp.data.db.current.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myweatherapp.domain.models.current.roomentities.CurrentX

@Dao
interface CurrentWeatherDAO {
    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)// onConflict in case of inserting repeated location
    // upsert == update and insert
    // long : the id that was inserted
    suspend fun upsert (currentX: CurrentX) : Long

    //READ
    @Query("SELECT * FROM current_weather")
    fun getAllCurrent () : LiveData<List<CurrentX>>

    //DELETE
    @Delete
    suspend fun deleteCurrent(currentX: CurrentX)
}