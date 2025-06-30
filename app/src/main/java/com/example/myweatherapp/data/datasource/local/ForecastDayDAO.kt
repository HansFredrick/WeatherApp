package com.example.myweatherapp.data.datasource.local
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myweatherapp.data.entities.forecastweather.room.ForecastDayEntity
import com.example.myweatherapp.data.entities.forecastweather.room.ForecastDayWrapper
import kotlinx.coroutines.flow.Flow

@Dao
interface ForecastDayDAO {

    @Upsert
    suspend fun upsert (forecastDay: ForecastDayEntity) : Long

    @Upsert
    suspend fun upsert(forecastDayEntities : List<ForecastDayEntity>): List<Long>

    //READ
    /*
    1:many
    location:forest day
     */
    @Transaction
    @Query("SELECT * FROM forecastDay WHERE locationId = :locationId")
     fun getLiveForecastWithDaysByLocation(locationId: Int): Flow<List<ForecastDayWrapper>>

    //DELETE
    @Delete
    suspend fun deleteForecastDay(forecastDay: ForecastDayEntity)
}