package com.example.myweatherapp.data.api

import com.example.myweatherapp.data.datasource.api.LocationApi
import com.example.myweatherapp.data.datasource.api.WeatherApi
import com.example.myweatherapp.util.Constants.Companion.LOCATION_BASE_URL
import com.example.myweatherapp.util.Constants.Companion.WEATHER_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{
        private val weatherRetrofit by lazy {
            val logging  = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val weatherApi by lazy{
            weatherRetrofit.create(WeatherApi::class.java)
        }

        private val locationRetrofit by lazy {
            val logging  = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(LOCATION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val locationApi by lazy{
            locationRetrofit.create(LocationApi::class.java)
        }
    }
}