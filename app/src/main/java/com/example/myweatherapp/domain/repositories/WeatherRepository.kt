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


    fun getCurrentWeather(q: String, aqi: String,dy:Int,alrt:String) = networkBoundResource(
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

                    val getForecastResponse = weatherApi.getForecastWeather(
                        location = q,
                        days = dy,
                        airQuality = aqi,
                        alerts = alrt
                    )
                    if (getForecastResponse.isSuccessful) {
                        val forecastResult = getForecastResponse.body()
                        if (forecastResult != null) {
                            upsertForecastWeather(forecastResult,savedLocationID.toInt())
                        }
                    }

                }
            }


        },
        onFetchFailed = {error->
            println("ERROR OCCURRED 1:${error.message}")
        }
    )

    private suspend fun upsertCurrentWeather(weatherResult: CurrentWeatherResponse) {
        val locationEntity = weatherResult.location.toEntity()
        val savedLocationID = locationDAO.saveEntity(locationEntity)
        currentWeatherDAO.upsert(currentWeather = weatherResult.current.toEntity(locationID = savedLocationID.toInt()))
    }


    private suspend fun upsertForecastWeather(remote: ForecastWeatherResponse, locationID: Int) {
        println("COUNT:"+remote.forecast.forecastDay.count())
       // println("AVG TEMP:"+remote.forecast.forecastDay.get(0).day.averageTemperatureCelsus)
        val forecastDayEntities = remote.forecast.forecastDay.map {
            it.toEntity(locationID)
        }

        val savedForecastDayIds = forecastDayDAO.saveEntity(forecastDayEntities = forecastDayEntities)


        val dayEntities = savedForecastDayIds.mapIndexed { index, id ->
            remote.forecast.forecastDay[index].day.toEntity(forecastID = id.toInt())
        }
        dayDAO.upsert(entities = dayEntities)
    }

}