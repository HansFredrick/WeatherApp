package com.example.myweatherapp.data.db.forecast

import androidx.room.TypeConverter
import com.example.myweatherapp.domain.models.forecast.AirQuality
import com.example.myweatherapp.domain.models.forecast.Condition
import com.example.myweatherapp.domain.models.forecast.AirQualityX
import com.example.myweatherapp.domain.models.forecast.Astro

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    //Converters of condition in forecast
    @TypeConverter
    fun fromCondition( condition: Condition):String{
        return condition.text
    }

    @TypeConverter
    fun toCondition (text:String): Condition {
        return Condition(0,text,text)

    }

    private val gson = Gson()

    @TypeConverter
    fun fromAirQualityX(airQualityX: AirQualityX):String{
        return gson.toJson(airQualityX)
    }
    @TypeConverter
    fun toAirQualityX(data: String): AirQualityX {
        val type = object : TypeToken<AirQualityX>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun fromAirQuality(airQuality: AirQuality):String{
        return gson.toJson(airQuality)
    }
    @TypeConverter
    fun toAirQuality(data: String): AirQuality {
        val type = object : TypeToken<AirQuality>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun fromAstro(astro: Astro):String{
        return gson.toJson(astro)
    }
    @TypeConverter
    fun toAstro(data: String): Astro {
        val type = object : TypeToken<Astro>() {}.type
        return gson.fromJson(data, type)
    }
}