package com.example.myweatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myweatherapp.data.datasource.local.location.LocationDAO
import com.example.myweatherapp.data.datasource.local.weather.CurrentWeatherDAO
import com.example.myweatherapp.data.datasource.local.weather.DayDAO
import com.example.myweatherapp.data.datasource.local.weather.ForecastDayDAO
import com.example.myweatherapp.data.datasource.local.weather.WeatherLocationDAO
import com.example.myweatherapp.data.entities.location.room.LocationEntity
import com.example.myweatherapp.data.entities.weather.currentweather.room.CurrentWeatherEntity
import com.example.myweatherapp.data.entities.weather.currentweather.room.WeatherLocationEntity
import com.example.myweatherapp.data.entities.weather.forecastweather.room.DayEntity
import com.example.myweatherapp.data.entities.weather.forecastweather.room.ForecastDayEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(
    entities = [
        CurrentWeatherEntity::class,
        WeatherLocationEntity::class,
        DayEntity::class,
        ForecastDayEntity::class,
        LocationEntity:: class
    ],
    version = 1
)

abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getCurrentWeatherDao(): CurrentWeatherDAO
    abstract fun getWeatherLocationDao(): WeatherLocationDAO
    abstract fun getDayDao(): DayDAO
    abstract fun getForecastDayDao(): ForecastDayDAO
    abstract fun getLocationDao(): LocationDAO
}

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext context: Context
    ): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_db.db"
        ).setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .fallbackToDestructiveMigration().build()
    }


}