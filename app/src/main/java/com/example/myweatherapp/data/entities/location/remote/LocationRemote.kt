package com.example.myweatherapp.data.entities.location.remote

import com.google.gson.annotations.SerializedName

class LocationRemote (
    @SerializedName("place_id") val placeID: Int,
    @SerializedName("name") val locationName: String,
    @SerializedName("display_name") val locationDisplayName: String,
    @SerializedName("lat") val latitude: String,
    @SerializedName("lan") val longitude : String
)