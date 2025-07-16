package com.example.myweatherapp.data.datasource.local.weather
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.myweatherapp.data.entities.weather.forecastweather.room.ForecastDayEntity
import com.example.myweatherapp.data.entities.weather.forecastweather.room.ForecastDayWrapper
import kotlinx.coroutines.flow.Flow

@Dao
interface ForecastDayDAO {

    @Upsert
    suspend fun upsert (forecastDay: ForecastDayEntity) : Long

    @Upsert
    suspend fun upsert(forecastDayEntities : List<ForecastDayEntity>): List<Long>

    @Transaction
    suspend fun saveEntity(forecastDayEntities : List<ForecastDayEntity>): List<Long>{
        val resultIds = mutableListOf<Long>()
        forecastDayEntities.forEach { entity ->
            val existingEntity = getForecastDayEntityByDate(entity.date)

            existingEntity?.apply {
                deleteForecastDay(existingEntity)
            }
            val id = upsert(forecastDay = entity)
            resultIds.add(id)
        }
        return resultIds
    }

    //READ
    /*
    1:many
    location:forest day
     */
    @Transaction
    @Query("SELECT * FROM forecastDay WHERE locationId = :locationId")
    fun getLiveForecastWithDaysByLocation(locationId: Int): Flow<List<ForecastDayWrapper>>

    @Query("SELECT * FROM forecastDay WHERE date = :date")
     fun getForecastDayEntityByDate(date:String): ForecastDayEntity

    //DELETE
    @Delete
    suspend fun deleteForecastDay(forecastDay: ForecastDayEntity)
}