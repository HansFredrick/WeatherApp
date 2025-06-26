package com.example.myweatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myweatherapp.data.datasource.local.CurrentWeatherDAO
import com.example.myweatherapp.data.entities.currentweather.room.CurrentWeatherEntity
import com.example.myweatherapp.data.datasource.local.LocationDAO
import com.example.myweatherapp.data.entities.currentweather.room.LocationEntity
import com.example.myweatherapp.data.datasource.local.DayDAO
import com.example.myweatherapp.data.entities.forecastweather.room.DayEntity
import com.example.myweatherapp.data.entities.forecastweather.room.ForecastDayEntity
import com.example.myweatherapp.data.datasource.local.ForecastDayDAO

@Database(
    entities = [
        CurrentWeatherEntity::class,
        LocationEntity:: class,
        DayEntity::class,
        ForecastDayEntity::class
               ],
    version = 1
)

abstract  class WeatherDatabase : RoomDatabase(){

    abstract fun getCurrentWeatherDao(): CurrentWeatherDAO
    abstract fun getLocationDao(): LocationDAO
    abstract fun getDayDao(): DayDAO
    abstract fun getForecastDayDao(): ForecastDayDAO

    companion object{
        @Volatile
        private var instance : WeatherDatabase? =null
        private val Lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WeatherDatabase:: class.java,
                "weather_db.db"
            ).build()
    }
}