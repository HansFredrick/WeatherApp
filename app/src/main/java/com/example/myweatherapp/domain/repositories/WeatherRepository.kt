package com.example.myweatherapp.domain.repositories

import com.example.myweatherapp.data.api.RetrofitInstance
import com.example.myweatherapp.data.db.current.database.CurrentWeatherDatabase
import com.example.myweatherapp.data.db.current.database.LocationDatabase
import com.example.myweatherapp.data.db.forecast.database.DayDatabase
import com.example.myweatherapp.data.db.forecast.database.ForecastDatabase
import com.example.myweatherapp.data.db.forecast.database.ForecastDayDatabase
import com.example.myweatherapp.data.db.forecast.database.HourDatabase
import com.example.myweatherapp.domain.models.current.roomentities.CurrentX
import com.example.myweatherapp.domain.models.current.roomentities.Location
import com.example.myweatherapp.domain.models.forecast.wrapper.ForecastWithDayAndHours
import com.example.myweatherapp.domain.repositories.mappers.ForecastMapper

class WeatherRepository(
    val currentWeatherDb :CurrentWeatherDatabase,
    val dayDb : DayDatabase,
    val forecastDayDb: ForecastDayDatabase,
    val hourDb: HourDatabase,
    val forcastDB : ForecastDatabase,
    val locationDB:LocationDatabase
) {
    suspend fun getCurrentWeather(q:String,aqi:String) =
        RetrofitInstance.api.getCurrentWeather(
            location = q,
        airQuality = aqi
        )

    suspend fun getForecastWeather(q:String,dy:Int,aqi:String,alrt:String)=
        RetrofitInstance.api.getForecastWeather(
            location = q,
            days = dy ,
            airQuality = aqi,
            alerts= alrt
        )


    /**
     * Fetches forecast data from the API and stores it into the local database.
     *
     * Flow:
     * 1. Makes a network call to fetch forecast data.
     * 2. Maps the API response to Room entities (Forecastday, Day, Hour).
     * 3. Uses ForecastDAO to insert the mapped data into their respective tables.
     *
     * @param q Location query (e.g., city name)
     * @param dy Number of forecast days
     * @param aqi Whether to include air quality ("yes"/"no")
     * @param alrt Whether to include weather alerts ("yes"/"no")
     */
    suspend fun fetchAndStoreForecast(q:String,dy:Int,aqi:String,alrt:String) {
        val response = RetrofitInstance.api.getForecastWeather(
            location = q,
            days = dy ,
            airQuality = aqi,
            alerts= alrt)
        if (response.isSuccessful && response.body() != null) {
            val forecastMapper = ForecastMapper()

            val forecast = response.body()!!
            val (forecastDays, days, hours) = forecastMapper.mapApiToEntities(forecast)

            // Save to Room
            forcastDB.getForecastDao().insertForecastDays(forecastDays)
            forcastDB.getForecastDao().insertDays(days)
            forcastDB.getForecastDao().insertHours(hours)
        }
    }
    /**
     * Fetches current weather data from the API and stores it in the local database.
     *
     * Flow:
     * 1. Makes a network call to fetch the current weather data.
     * 2. Extracts the current weather and location objects from the response.
     * 3. Persists the data into their respective Room tables:
     *    - Current weather → CurrentWeatherDao
     *    - Location info → LocationDao
     *
     * @param q Location query (e.g., city name or coordinates)
     * @param aqi Whether to include air quality data ("yes"/"no")
     */
    suspend fun fetchAndStoreCurrent(q:String,aqi:String) {
        val response =RetrofitInstance.api.getCurrentWeather(
            location = q,
            airQuality = aqi
        )
        if (response.isSuccessful && response.body() != null) {
            val currentWeather = response.body()!!
            currentWeatherDb.getCurrentWeatherDao().upsert(currentWeather.current)
            locationDB.getLocationDao().upsert(currentWeather.location)
        }
    }

    suspend fun getForecastDayWithHoursFromDB():List<ForecastWithDayAndHours>{
        return  forcastDB.getForecastDao().getForecastWithDayAndHours()
    }

    suspend fun getLocationByNameFromDB(q:String): Location {
        return locationDB.getLocationDao().getLocationWithCurrentWeather(q).location
    }

    suspend fun getCurrentWeatherByLocationFromDB(q:String):CurrentX{
        return locationDB.getLocationDao().getLocationWithCurrentWeather(q).current
    }






}