package com.example.myweatherapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myweatherapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setupWithNavController(findNavController(R.id.weatherNavHostFragment))

    }
}