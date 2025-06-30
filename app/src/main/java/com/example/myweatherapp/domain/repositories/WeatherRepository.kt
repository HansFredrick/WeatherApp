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
import com.example.myweatherapp.data.mappers.toDomain2
import com.example.myweatherapp.data.mappers.toEntity
import com.example.myweatherapp.domain.models.forecast.ForecastWeather
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
            /*
            output should have type WeatherForecast in  domain.models
            - WeatherForecast contains list fo ForecastDay
            - ForecastDayWrapper only contain Forecastdays
             */
            currentWeatherDAO.getLiveCurrentWeatherByLocation(locationName = q).map {
                val forecastX = it?.toDomain2()
                forecastX?.let { it1 ->
                    ForecastWeather(
                        forecast = it1
                    )
                }

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











}