//package com.example.myweatherapp.data.db.current.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.room.TypeConverters
//import com.example.myweatherapp.data.db.current.CurrentConverters
//import com.example.myweatherapp.data.db.current.DAO.CurrentWeatherDAO
//import com.example.myweatherapp.domain.models.current.roomentities.CurrentX
//import com.example.myweatherapp.domain.models.current.roomentities.Location
//
//@Database(
//    entities = [CurrentX :: class,
//        Location::class],
//    version = 1
//)
//
//@TypeConverters(CurrentConverters::class)
//abstract  class CurrentWeatherDatabase : RoomDatabase(){
//
//     abstract fun getCurrentWeatherDao(): CurrentWeatherDAO
//
//     companion object{
//         @Volatile
//         private var instance : CurrentWeatherDatabase? =null
//         private val Lock = Any()
//
//         operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
//             instance ?: createDatabase(context).also { instance = it}
//         }
//
//         private fun createDatabase(context: Context) =
//             Room.databaseBuilder(
//                 context.applicationContext,
//                 CurrentWeatherDatabase:: class.java,
//                 "current_weather_db.db"
//             ).build()
//     }
//}