package com.example.myweatherapp.data.db.forecast.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myweatherapp.data.db.forecast.Converters
import com.example.myweatherapp.data.db.forecast.DAO.ForecastDayDAO
import com.example.myweatherapp.domain.models.forecast.Day
import com.example.myweatherapp.domain.models.forecast.Forecastday
import com.example.myweatherapp.domain.models.forecast.Hour

@Database(
    entities = [Forecastday :: class,
               Day:: class,
               Hour:: class],
    version = 1
)

@TypeConverters(Converters::class)
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