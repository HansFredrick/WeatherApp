package com.example.myweatherapp.data.datasource.module

import com.example.myweatherapp.data.api.RetrofitInstance
import com.example.myweatherapp.data.datasource.api.LocationApi
import com.example.myweatherapp.data.datasource.api.WeatherApi
import com.example.myweatherapp.data.datasource.local.location.LocationDAO
import com.example.myweatherapp.data.datasource.local.weather.CurrentWeatherDAO
import com.example.myweatherapp.data.datasource.local.weather.DayDAO
import com.example.myweatherapp.data.datasource.local.weather.ForecastDayDAO
import com.example.myweatherapp.data.datasource.local.weather.WeatherLocationDAO
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

    @Provides
    @Singleton
    fun provideLocationApi(): LocationApi = RetrofitInstance.locationApi

    @Provides
    @Singleton
    fun provideWeatherLocationDAO(database: WeatherDatabase): WeatherLocationDAO {
        return database.getWeatherLocationDao()
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

    @Provides
    @Singleton
    fun provideLocationDAO(database: WeatherDatabase): LocationDAO {
        return database.getLocationDao()
    }

}
