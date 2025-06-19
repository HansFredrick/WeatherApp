package com.example.myweatherapp.data.entities.currentweather

import com.google.gson.annotations.SerializedName

class LocationRemote(
    val name : String,
    val region : String,
    val country : String,
    @SerializedName("tz_id")val timeZone : String,
    @SerializedName("localtime")val localTime: String) {

}