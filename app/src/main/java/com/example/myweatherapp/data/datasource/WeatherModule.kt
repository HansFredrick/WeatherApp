package com.example.myweatherapp.data.datasource

import com.example.myweatherapp.data.api.RetrofitInstance
import com.example.myweatherapp.data.datasource.local.CurrentWeatherDAO
import com.example.myweatherapp.data.datasource.local.DayDAO
import com.example.myweatherapp.data.datasource.local.ForecastDayDAO
import com.example.myweatherapp.data.datasource.local.LocationDAO
import com.example.myweatherapp.data.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WeatherModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi = RetrofitInstance.weatherApi

//    @Provides
//    @Singleton
//    fun provideLocationApi():LocationApi = RetrofitInstance.locationApi

    @Provides
    @Singleton
    fun provideLocationDAO(database: WeatherDatabase): LocationDAO {
        return database.getLocationDao()
    }

    @Provides
    @Singleton
    fun provideCurrentWeatherDAO(database: WeatherDatabase): CurrentWeatherDAO {
        return database.getCurrentWeatherDao()
    }

    @Provides
    @Singleton
    fun provideDayDAO(database: WeatherDatabase): DayDAO {
        return database.getDayDao()
    }

    @Provides
    @Singleton
    fun provideForecastDayDAO(database: WeatherDatabase): ForecastDayDAO {
        return database.getForecastDayDao()
    }

}
