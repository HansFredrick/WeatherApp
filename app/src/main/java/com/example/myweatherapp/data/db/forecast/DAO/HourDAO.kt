package com.example.myweatherapp.data.db.forecast.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myweatherapp.domain.models.forecast.Hour

@Dao
interface HourDAO {
    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)// onConflict in case of inserting repeated location
    // upsert == update and insert
    // long : the id that was inserted
    suspend fun upsert (hour: Hour) : Long

    //READ
    @Query("SELECT * FROM hours")
    fun getAllLocations () : LiveData<List<Hour>>

    //DELETE
    @Delete
    suspend fun deleteLocation(day: Hour)
}