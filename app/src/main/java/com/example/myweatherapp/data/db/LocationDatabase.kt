package com.example.myweatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myweatherapp.domain.model.current.Location
import okio.Lock

@Database(
    entities = [Location :: class],
    version = 1
)
abstract class LocationDatabase : RoomDatabase(){

    abstract fun getLocationDao(): LocationDAO

    companion object{
        @Volatile
        private var instance :LocationDatabase? =null
        private val Lock = Any()

        operator fun invoke(context: Context) = Companion.instance ?: synchronized(Lock) {
            instance?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                LocationDatabase:: class.java,
                "location_db.db"
            ).build()
    }
}