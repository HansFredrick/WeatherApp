package com.example.myweatherapp.domain.repositories.mappers

import com.example.myweatherapp.domain.models.forecast.roomentities.Day
import com.example.myweatherapp.domain.models.forecast.Forecast
import com.example.myweatherapp.domain.models.forecast.roomentities.Forecastday
import com.example.myweatherapp.domain.models.forecast.roomentities.Hour

class ForecastMapper {
    fun mapApiToEntities(api: Forecast): Triple<List<Forecastday>, List<Day>, List<Hour>> {
        val forecastDays = mutableListOf<Forecastday>()
        val days = mutableListOf<Day>()
        val hours = mutableListOf<Hour>()

        api.forecast.forecastday.forEachIndexed { index, apiForecastDay ->
            val id = index + 1  // Or use UUID or server ID

            forecastDays.add(
                Forecastday(
                    forecast_day_id = id,
                    date = apiForecastDay.date,
                    date_epoch = apiForecastDay.date_epoch,
                    astro = apiForecastDay.astro
                )
            )

            days.add(apiForecastDay.day.copy(forecast_day_id = id))

            hours.addAll(apiForecastDay.hour.map { it.copy(forecast_day_id = id) })
        }

        return Triple(forecastDays, days, hours)
    }

}