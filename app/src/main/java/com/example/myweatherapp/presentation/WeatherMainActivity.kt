package com.example.myweatherapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myweatherapp.R
import com.example.myweatherapp.data.db.current.database.CurrentWeatherDatabase
import com.example.myweatherapp.data.db.current.database.LocationDatabase
import com.example.myweatherapp.data.db.forecast.database.DayDatabase
import com.example.myweatherapp.data.db.forecast.database.ForecastDayDatabase
import com.example.myweatherapp.data.db.forecast.database.HourDatabase
import com.example.myweatherapp.domain.repositories.LocationRepository
import com.example.myweatherapp.domain.repositories.WeatherRepository
import com.example.myweatherapp.presentation.viewmodel.LocationViewModel
import com.example.myweatherapp.presentation.viewmodel.WeatherViewModel
import com.example.myweatherapp.presentation.viewmodel.factories.LocationViewModelProviderFactory
import com.example.myweatherapp.presentation.viewmodel.factories.WeatherViewModelProviderFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class WeatherMainActivity : AppCompatActivity() {

    lateinit var weatherViewModel : WeatherViewModel
    lateinit var locationViewModel : LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        // for Home fragment
        val weatherRepository = WeatherRepository(
            CurrentWeatherDatabase(this),
            DayDatabase(this),
            ForecastDayDatabase(this),
            HourDatabase(this))

        val weatherViewModelProviderFactory = WeatherViewModelProviderFactory(weatherRepository)

        weatherViewModel = ViewModelProvider(this,weatherViewModelProviderFactory)
            .get(WeatherViewModel::class.java)

        //for locations fragment
        val locationRepository = LocationRepository(
            LocationDatabase(this))

        val locationViewModelProviderFactory = LocationViewModelProviderFactory(locationRepository)

        locationViewModel = ViewModelProvider(this,locationViewModelProviderFactory)
            .get(LocationViewModel::class.java)



        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setupWithNavController(findNavController(R.id.weatherNavHostFragment))

    }
}