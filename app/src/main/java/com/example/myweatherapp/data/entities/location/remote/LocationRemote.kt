package com.example.myweatherapp.data.entities.location.remote

import com.google.gson.annotations.SerializedName

class LocationRemote (
    @SerializedName("place_id") val placeID: Int,
    @SerializedName("display_name") val displayName: String,
    @SerializedName("lat") val latitude: String,
    @SerializedName("lon") val longitude : String
)