package com.example.myweatherapp.data.db.current.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myweatherapp.data.db.current.DAO.LocationDAO
import com.example.myweatherapp.domain.model.current.Location

@Database(
    entities = [Location :: class],
    version = 1
)
abstract class LocationDatabase : RoomDatabase(){

    abstract fun getLocationDao(): LocationDAO

    companion object{
        @Volatile
        private var instance : LocationDatabase? =null
        private val Lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                LocationDatabase:: class.java,
                "location_db.db"
            ).build()
    }
}