package com.example.myweatherapp.data.entities

import com.google.gson.annotations.SerializedName

class LocationRemote(
    val name : String,
    val region : String,
    val country : String,
    @SerializedName("lat") val latitude : Double,
    @SerializedName("lon")val longitude : Double,
    @SerializedName("tz_id")val timeZone : String,
    @SerializedName("localtime_epoch")val localTimeEpoch : Int,
    @SerializedName("localtime")val localTime: String) {

}