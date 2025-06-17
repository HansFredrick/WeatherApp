package com.example.myweatherapp.data.db.forecast.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myweatherapp.data.db.forecast.Converters
import com.example.myweatherapp.data.db.forecast.DAO.HourDAO
import com.example.myweatherapp.domain.models.forecast.Forecastday
import com.example.myweatherapp.domain.models.forecast.Hour

@Database(
    entities = [Hour::class,
        Forecastday :: class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class HourDatabase : RoomDatabase(){

    abstract fun getHourDao(): HourDAO

    companion object{
        @Volatile
        private var instance : HourDatabase? =null
        private val Lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HourDatabase:: class.java,
                "hour_db.db"
            ).build()
    }
}