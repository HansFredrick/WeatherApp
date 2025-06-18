package com.example.myweatherapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.data.entities.CurrentWeatherResponse
import com.example.myweatherapp.domain.models.forecast.wrapper.ForecastWithDayAndHours
import com.example.myweatherapp.domain.repositories.WeatherRepository
import com.example.myweatherapp.util.Resource
import kotlinx.coroutines.launch

class WeatherViewModel(
    val weatherRepository : WeatherRepository
): ViewModel() {

    val weatherCurrentWeatherResponse: MutableLiveData<Resource<CurrentWeatherResponse>> = MutableLiveData()

    val weatherForecast: MutableLiveData<List<ForecastWithDayAndHours>> = MutableLiveData()

    var weatherForecastDays = 10
    var aqi = "yes"
    var alerts = "yes"

//    fun fetchAndStoreForecast(q: String) {
//        viewModelScope.launch {
//            weatherRepository.fetchAndStoreForecast(q, weatherForecastDays, aqi, alerts)
//        }
//    }
//
//    fun fetchAndStoreCurrent(q: String) {
//        viewModelScope.launch {
//            weatherRepository.fetchAndStoreCurrent(q, aqi)
//        }
//    }
//
//    fun loadForecastFromDB() = viewModelScope.launch {
//        weatherForecast.postValue(weatherRepository.getForecastDayWithHoursFromDB())
//    }
//
//    fun loadCurrentForecast() = viewModelScope.launch {
//        weatherForecast.postValue(weatherRepository.getForecastDayWithHoursFromDB())
//    }


}