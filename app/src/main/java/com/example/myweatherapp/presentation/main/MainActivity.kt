package com.example.myweatherapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myweatherapp.R
import com.example.myweatherapp.presentation.viewmodel.LocationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel : MainViewModel
    lateinit var locationViewModel : LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_weather)

        //Bottom navigatiom
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setupWithNavController(findNavController(R.id.weatherNavHostFragment))

    }
}