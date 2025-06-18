package com.example.myweatherapp.data.db.forecast.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myweatherapp.data.db.forecast.Converters
import com.example.myweatherapp.data.db.forecast.DAO.ForecastDAO
import com.example.myweatherapp.domain.models.forecast.roomentities.Day
import com.example.myweatherapp.domain.models.forecast.roomentities.Forecastday
import com.example.myweatherapp.domain.models.forecast.roomentities.Hour

@Database(
    entities = [Forecastday :: class,
        Day:: class,
        Hour:: class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class ForecastDatabase : RoomDatabase(){

    abstract fun getForecastDao(): ForecastDAO

    companion object{
        @Volatile
        private var instance : ForecastDatabase? =null
        private val Lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ForecastDatabase:: class.java,
                "forecast_db.db"
            ).build()
    }
}