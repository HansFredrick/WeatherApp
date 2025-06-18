package com.example.myweatherapp.data.db.current

import androidx.room.TypeConverter
import com.example.myweatherapp.domain.models.current.AirQuality
import com.example.myweatherapp.domain.models.current.Condition
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson

class CurrentConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromAirQuality(airQuality: AirQuality): String = gson.toJson(airQuality)

    @TypeConverter
    fun toAirQuality(data: String): AirQuality {
        val type = object : TypeToken<AirQuality>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun fromCondition(condition: Condition): String = gson.toJson(condition)

    @TypeConverter
    fun toCondition(data: String): Condition {
        val type = object : TypeToken<Condition>() {}.type
        return gson.fromJson(data, type)
    }
}