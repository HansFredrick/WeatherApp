package com.example.myweatherapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myweatherapp.domain.model.current.Location


@Dao
interface LocationDAO {
    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)// onConflict in case of inserting repeated location
    // upsert == update and insert
    // long : the id that was inserted
    suspend fun upsert (location: Location) : Long

    //READ
    @Query("SELECT * FROM locations")
    fun getAllLocations () : LiveData<List<Location>>

    //DELETE
    @Delete
    suspend fun deleteLocation(location: Location)
}