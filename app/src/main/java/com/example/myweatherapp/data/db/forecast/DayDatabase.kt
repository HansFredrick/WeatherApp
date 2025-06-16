package com.example.myweatherapp.data.db.forecast

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myweatherapp.domain.model.forecast.Day

@Database(
    entities = [Day :: class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class DayDatabase : RoomDatabase(){

    abstract fun getDayDao(): DayDAO

    companion object{
        @Volatile
        private var instance : DayDatabase? =null
        private val Lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DayDatabase:: class.java,
                "day_db.db"
            ).build()
    }
}