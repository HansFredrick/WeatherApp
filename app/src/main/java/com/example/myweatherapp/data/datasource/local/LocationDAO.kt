package com.example.myweatherapp.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myweatherapp.data.entities.currentweather.room.WeatherLocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)// onConflict in case of inserting repeated location
    suspend fun upsert (location: WeatherLocationEntity) : Long

    @Transaction
    suspend fun saveEntity(location: WeatherLocationEntity):Long{
        val existingLocationEntity = getLocationByName(name = location.name)
        //If existing location is not null
        //trigger apply block
        existingLocationEntity?.apply {
            deleteLocation(location = this)
        }

        return upsert(location = location)
    }



    //READ
    @Query("SELECT * FROM location")
    fun getAllLocations () : List<WeatherLocationEntity>

    @Query("SELECT * FROM location WHERE name = :name")
    suspend fun getLocationByName(name: String): WeatherLocationEntity?

    //DELETE
    @Delete
    suspend fun deleteLocation(location: WeatherLocationEntity)
}