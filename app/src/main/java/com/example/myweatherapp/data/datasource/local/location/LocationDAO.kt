package com.example.myweatherapp.data.datasource.local.location

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myweatherapp.data.entities.location.room.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)// onConflict in case of inserting repeated location
    suspend fun upsert (location: LocationEntity) : Long

    @Transaction
    suspend fun saveEntity(location: LocationEntity):Long{
        val existingLocationEntity = location.placeID?.let { getLocationById(id = it) }
        //If existing location is not null
        //trigger apply block
        existingLocationEntity?.apply {
            deleteLocation(location = this)
        }

        return upsert(location = location)
    }

    //READ
    @Query("SELECT * FROM location")
    fun getAllLocations(): Flow<List<LocationEntity>>

    @Query("SELECT * FROM location WHERE placeId = :id")
    suspend fun getLocationById(id: Int): LocationEntity?

    //DELETE
    @Delete
    suspend fun deleteLocation(location: LocationEntity)
}