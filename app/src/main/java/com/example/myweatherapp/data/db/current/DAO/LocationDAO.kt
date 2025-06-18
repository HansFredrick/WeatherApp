package com.example.myweatherapp.data.db.current.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myweatherapp.domain.models.current.roomentities.Location
import com.example.myweatherapp.domain.models.current.wrapper.LocationWithCurrentWeather


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

    @Query("SELECT * FROM locations WHERE name = :name")
    suspend fun getLocationByName(name: String): Location?

    @Transaction
    @Query("SELECT * FROM locations WHERE  name = :name")
    suspend fun getLocationWithCurrentWeather(name: String): LocationWithCurrentWeather

    //DELETE
    @Delete
    suspend fun deleteLocation(location: Location)
}