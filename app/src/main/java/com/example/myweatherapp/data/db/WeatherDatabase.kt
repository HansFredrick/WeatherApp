package com.example.myweatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myweatherapp.data.datasource.local.CurrentWeatherDAO
import com.example.myweatherapp.data.datasource.local.DayDAO
import com.example.myweatherapp.data.datasource.local.ForecastDayDAO
import com.example.myweatherapp.data.datasource.local.LocationDAO
import com.example.myweatherapp.data.entities.currentweather.room.CurrentWeatherEntity
import com.example.myweatherapp.data.entities.currentweather.room.WeatherLocationEntity
import com.example.myweatherapp.data.entities.forecastweather.room.DayEntity
import com.example.myweatherapp.data.entities.forecastweather.room.ForecastDayEntity
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
        ForecastDayEntity::class
    ],
    version = 1
)

abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getCurrentWeatherDao(): CurrentWeatherDAO
    abstract fun getLocationDao(): LocationDAO
    abstract fun getDayDao(): DayDAO
    abstract fun getForecastDayDao(): ForecastDayDAO
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