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

    @Insert(onConflict = OnConflictStrategy.REPLACE)// onConflict in case of inserting repeated location
    suspend fun upsert (location: LocationEntity) : Long

    suspend fun saveEntity(location: LocationEntity):Long{
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
    fun getAllLocations () : LiveData<List<LocationEntity>>

    @Query("SELECT * FROM location WHERE name = :name")
    suspend fun getLocationByName(name: String): LocationEntity?

    //DELETE
    @Delete
    suspend fun deleteLocation(location: LocationEntity)
}