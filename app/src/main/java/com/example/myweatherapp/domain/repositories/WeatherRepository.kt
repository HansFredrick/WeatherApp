package com.example.myweatherapp.domain.repositories

import com.example.myweatherapp.data.datasource.WeatherApi
import com.example.myweatherapp.data.datasource.local.CurrentWeatherDAO
import com.example.myweatherapp.data.datasource.local.DayDAO
import com.example.myweatherapp.data.datasource.local.ForecastDayDAO
import com.example.myweatherapp.data.datasource.local.LocationDAO
import com.example.myweatherapp.data.entities.forecastweather.remote.ForecastWeatherResponse
import com.example.myweatherapp.data.mappers.toDomain
import com.example.myweatherapp.data.mappers.toEntity
import com.example.myweatherapp.domain.models.currentweather.Location
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val locationDAO: LocationDAO,
    private val currentWeatherDAO: CurrentWeatherDAO,
    private val forecastDayDAO: ForecastDayDAO,
    private val dayDAO: DayDAO,

    ) {
    //store db,

    suspend fun getCurrentWeather(q: String, aqi: String): Result<Location> {
        val weatherRemote = weatherApi.getCurrentWeather(
            location = q,
            airQuality = aqi
        ).body()

        if (weatherRemote?.location == null)
            return Result.failure(IllegalStateException("Location is null"))

        val savedLocationID = locationDAO.upsert(location = weatherRemote.location.toEntity())
        val weather = weatherRemote?.current?.toEntity(locationID = savedLocationID.toInt())


        return Result.success(weatherRemote.location.toDomain())
    }


    suspend fun getForecastWeather(
        q: String,
        dy: Int,
        aqi: String,
        alrt: String
    ): Response<ForecastWeatherResponse> {
        return weatherApi!!.getForecastWeather(
            location = q,
            days = dy,
            airQuality = aqi,
            alerts = alrt
        )
    }

    /*
    Create a function the stores api response to room
     */

    suspend fun fetchAndStoreResponses(q: String, dy: Int, aqi: String, alrt: String) {
        val currentWeatherResponse = weatherApi!!.getCurrentWeather(
            location = q,
            airQuality = aqi
        )

        val forecastResponse = weatherApi!!.getForecastWeather(
            location = q,
            days = dy,
            airQuality = aqi,
            alerts = alrt
        )

    }


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
//    suspend fun fetchAndStoreForecast(q:String,dy:Int,aqi:String,alrt:String) {
//        val response = RetrofitInstance.api.getForecastWeather(
//            location = q,
//            days = dy ,
//            airQuality = aqi,
//            alerts= alrt)
//        if (response.isSuccessful && response.body() != null) {
//            val forecastMapper = ForecastMapper()
//
//            val forecast = response.body()!!
//            val (forecastDays, days, hours) = forecastMapper.mapApiToEntities(forecast)
//
//            // Save to Room
//            forcastDB.getForecastDao().insertForecastDays(forecastDays)
//            forcastDB.getForecastDao().insertDays(days)
//            forcastDB.getForecastDao().insertHours(hours)
//        }
//    }
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
//    suspend fun fetchAndStoreCurrent(q:String,aqi:String) {
//        val response =RetrofitInstance.api.getCurrentWeather(
//            location = q,
//            airQuality = aqi
//        )
//        if (response.isSuccessful && response.body() != null) {
//            val currentWeather = response.body()!!
//            currentWeatherDb.getCurrentWeatherDao().upsert(currentWeather.current)
//            locationDB.getLocationDao().upsert(currentWeather.location)
//        }
//    }
//
//    suspend fun getForecastDayWithHoursFromDB():List<ForecastWithDayAndHours>{
//        return  forcastDB.getForecastDao().getForecastWithDayAndHours()
//    }
//
//    suspend fun getLocationByNameFromDB(q:String): Location {
//        return locationDB.getLocationDao().getLocationWithCurrentWeather(q).location
//    }
//
//    suspend fun getCurrentWeatherByLocationFromDB(q:String):CurrentX{
//        return locationDB.getLocationDao().getLocationWithCurrentWeather(q).current
//    }


}