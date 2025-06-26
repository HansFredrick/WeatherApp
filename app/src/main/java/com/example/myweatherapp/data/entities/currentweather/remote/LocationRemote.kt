package com.example.myweatherapp.data.entities.currentweather.remote

import com.google.gson.annotations.SerializedName

class LocationRemote(
    val name : String,
    val country : String,
    @SerializedName("tz_id")val timeZone : String,
    @SerializedName("localtime")val localTime: String) {

}