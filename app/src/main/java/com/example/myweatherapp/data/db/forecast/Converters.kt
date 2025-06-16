package com.example.myweatherapp.data.db.forecast

import androidx.room.TypeConverter
import com.example.myweatherapp.domain.model.current.Condition

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
}