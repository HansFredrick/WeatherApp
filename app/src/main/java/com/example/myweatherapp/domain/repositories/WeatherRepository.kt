package com.example.myweatherapp.domain.repositories

import com.example.myweatherapp.data.api.networkBoundResource
import com.example.myweatherapp.data.datasource.WeatherApi
import com.example.myweatherapp.data.datasource.local.CurrentWeatherDAO
import com.example.myweatherapp.data.datasource.local.DayDAO
import com.example.myweatherapp.data.datasource.local.ForecastDayDAO
import com.example.myweatherapp.data.datasource.local.LocationDAO
import com.example.myweatherapp.data.entities.currentweather.remote.CurrentWeatherResponse
import com.example.myweatherapp.data.entities.forecastweather.remote.ForecastWeatherResponse
import com.example.myweatherapp.data.mappers.toDomain
import com.example.myweatherapp.data.mappers.toEntity
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val locationDAO: LocationDAO,
    private val currentWeatherDAO: CurrentWeatherDAO,
    private val forecastDayDAO: ForecastDayDAO,
    private val dayDAO: DayDAO,

    ) {


    fun getCurrentWeather(q: String, aqi: String) = networkBoundResource(
        query = { // first get data
            currentWeatherDAO.getLiveCurrentWeatherByLocation(locationName = q).map {
                it?.toDomain()
            }
        },
        shouldFetch = { true },
        fetch = {
            weatherApi.getCurrentWeather(
                location = q,
                airQuality = aqi
            )
        },
        saveFetchResult = { response ->
            if (response.isSuccessful) {
                val weatherResult = response.body()
                if (weatherResult != null) {

                    upsertCurrentWeather(weatherResult)
                    val locationEntity = weatherResult.location.toEntity()
                    val savedLocationID = locationDAO.saveEntity(locationEntity)
                    currentWeatherDAO.upsert(currentWeather = weatherResult.current.toEntity(locationID = savedLocationID.toInt()))
                }
            }
        },
        onFetchFailed = {error->
            println("ERROR OCCURRED:${error.message}")
        }
    )

    private suspend fun upsertCurrentWeather(weatherResult: CurrentWeatherResponse) {
        val locationEntity = weatherResult.location.toEntity()
        val savedLocationID = locationDAO.saveEntity(locationEntity)
        currentWeatherDAO.upsert(currentWeather = weatherResult.current.toEntity(locationID = savedLocationID.toInt()))
    }



    suspend fun getForecastWeather( q: String,  dy: Int, aqi: String, alrt: String, locationID: Int ) = networkBoundResource(
        query = { // get DAO
            forecastDayDAO.getLiveForecastWithDayByLocation(locationID).map {
                it.toDomain()
            }
        },
        shouldFetch = { true },
        fetch = {
            weatherApi.getForecastWeather(
            location = q,
            days = dy,
            airQuality = aqi,
            alerts = alrt
        )
        },
        saveFetchResult = { response ->
            if (response.isSuccessful) {
                val forecastResult = response.body()
                if (forecastResult != null) {
                    upsertForecastWeather(forecastResult,locationID)
                }
            }
        },
        onFetchFailed = {error->
            println("ERROR OCCURRED:${error.message}")
        }
    )

    private suspend fun upsertForecastWeather(remote: ForecastWeatherResponse, locationID: Int) {
        println(remote.forecast.forecastDay.count())
        val forecastDayEntities = remote.forecast.forecastDay.map {
            it.toEntity(locationID)
        }

        val savedForecastDayIds = forecastDayDAO.upsert(forecastDayEntities = forecastDayEntities)


        val dayEntities = savedForecastDayIds.mapIndexed { index, id ->
            remote.forecast.forecastDay[index].day.toEntity(forecastID = id.toInt())
        }
        dayDAO.upsert(entities = dayEntities)
    }

//    suspend fun getForecastWeather( q: String,  dy: Int, aqi: String, alrt: String, locationID: Int ): Result<ForecastWeather> {
//        val getForecastWeatherResponse = weatherApi.getForecastWeather(
//            location = q,
//            days = dy,
//            airQuality = aqi,
//            alerts = alrt
//        )
//        if (!getForecastWeatherResponse.isSuccessful)
//            return Result.failure(IllegalStateException("Fetching forecast weather failed"))
//
//        val forecastWeatherRemote = getForecastWeatherResponse.body()
//
//
//        upsertForecastWeather(remote = forecastWeatherRemote!!, locationID = locationID)
//
//        return Result.success(forecastWeatherRemote!!.toDomain())
//
//    }


    // fun for passing a domain model
//    suspend fun getCurrentWeather(q: String, aqi: String) =
//        flow<Result<Pair<Weather, ForecastWeather>>> {
//            //Fetch from Retrofit // not running if app has no internet/offline
//            //Convert to Domain
//            //Domain to UI
//
//            //Get saved current weather locally
//            //Fetch from retrofit
//            //Save fetched from retrofit to entity
//            //Get saved current weather Locally
//
//
//
//
//            val getCurrentWeatherResponse = weatherApi.getCurrentWeather(
////                location = q,
////                airQuality = aqi
////            )
//
//            if (!getCurrentWeatherResponse.isSuccessful)
//                return Result.failure(IllegalStateException("Fetching weather failed"))
//
//            val weatherRemote = getCurrentWeatherResponse.body()
//            upsertCurrentWeather(remote = weatherRemote)
//
//            if (weatherRemote?.location == null)
//                return Result.failure(IllegalStateException("Location is null"))
//
//            return Result.success((weatherRemote.toDomain()))
//        }







}