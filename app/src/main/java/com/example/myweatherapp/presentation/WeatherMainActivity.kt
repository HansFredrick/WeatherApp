package com.example.myweatherapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myweatherapp.R
import com.example.myweatherapp.presentation.viewmodelold.WeatherViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class WeatherMainActivity : AppCompatActivity() {

    lateinit var weatherViewModel : WeatherViewModel
//    lateinit var locationViewModel : LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // for Home fragment
//        val weatherRepository = WeatherRepository(
//            CurrentWeatherDatabase(this),
//            DayDatabase(this),
//            ForecastDayDatabase(this),
//            HourDatabase(this),
//            ForecastDatabase(this),
//            LocationDatabase(this))

//        val weatherViewModelProviderFactory = WeatherViewModelProviderFactory(weatherRepository)

//        weatherViewModel = ViewModelProvider(this,weatherViewModelProviderFactory)
//            .get(WeatherViewModel::class.java)

        //for locations fragment
//        val locationRepository = LocationRepository(
//            LocationDatabase(this))

//        val locationViewModelProviderFactory = LocationViewModelProviderFactory(locationRepository)

//        locationViewModel = ViewModelProvider(this,locationViewModelProviderFactory)
//            .get(LocationViewModel::class.java)

        setContentView(R.layout.activity_weather)

        //Bottom navigatiom
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setupWithNavController(findNavController(R.id.weatherNavHostFragment))

    }
}