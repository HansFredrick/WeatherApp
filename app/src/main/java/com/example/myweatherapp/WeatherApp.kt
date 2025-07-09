package com.example.myweatherapp

import android.app.Application
import androidx.room.Room
import com.example.myweatherapp.data.db.WeatherDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApp : Application() {
}