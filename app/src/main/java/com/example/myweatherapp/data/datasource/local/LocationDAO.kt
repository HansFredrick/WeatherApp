package com.example.myweatherapp.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myweatherapp.data.entities.currentweather.room.LocationEntity

@Dao
interface LocationDAO {
    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)// onConflict in case of inserting repeated location
    // upsert == update and insert
    // long : the id that was inserted
    suspend fun upsert (location: LocationEntity) : Long

    //READ
    @Query("SELECT * FROM location")
    fun getAllLocations () : LiveData<List<LocationEntity>>

    @Query("SELECT * FROM location WHERE name = :name")
    suspend fun getLocationByName(name: String): LocationEntity?

    //DELETE
    @Delete
    suspend fun deleteLocation(location: LocationEntity)
}