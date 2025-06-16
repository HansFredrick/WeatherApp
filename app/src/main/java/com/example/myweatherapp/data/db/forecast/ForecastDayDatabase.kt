package com.example.myweatherapp.data.db.forecast

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myweatherapp.domain.model.forecast.Forecastday

@Database(
    entities = [Forecastday :: class],
    version = 1
)
abstract class ForecastDayDatabase : RoomDatabase(){

    abstract fun getForecastDayDao(): ForecastDayDAO

    companion object{
        @Volatile
        private var instance : ForecastDayDatabase? =null
        private val Lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ForecastDayDatabase:: class.java,
                "forecast_day_db.db"
            ).build()
    }
}