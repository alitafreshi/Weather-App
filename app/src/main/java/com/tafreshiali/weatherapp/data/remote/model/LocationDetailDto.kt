package com.tafreshiali.weatherapp.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class LocationDetailDto(
    @SerialName("country")
    val countryName: String?,
    @SerialName("lat")
    val latitude: Double?,
    @SerialName("lon")
    val longitude: Double?,
    @SerialName("localtime")
    val localDateTime: String?,
    @SerialName("name")
    val cityName: String?,
    @SerialName("region")
    val regionName: String?,
    @SerialName("tz_id")
    val timeZone: String?
)