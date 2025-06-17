package com.example.myweatherapp.data.db.current

import androidx.room.TypeConverter
import com.example.myweatherapp.domain.model.current.AirQuality
import com.example.myweatherapp.domain.model.current.Condition
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson

class Converters {
// for current
    private val gson = Gson()

    @TypeConverter
    fun fromAirQuality ( airQuality:AirQuality):String{
        return gson.toJson(airQuality)
    }
    @TypeConverter
    fun toAirQuality(data: String): AirQuality {
        val type = object : TypeToken<AirQuality>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun fromCondition( condition: Condition):String{
        return condition.text
    }

    @TypeConverter
    fun toCondition (text:String):Condition{
        return Condition(0,text,text)

    }

}