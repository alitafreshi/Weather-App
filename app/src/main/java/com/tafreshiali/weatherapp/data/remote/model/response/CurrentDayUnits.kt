package com.tafreshiali.weatherapp.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class CurrentDayUnits(
    @SerialName("interval")
    val interval: String?,
    @SerialName("is_day")
    val isDay: String?,
    @SerialName("time")
    val time: String?,
    @SerialName("wind_speed_10m")
    val windSpeed10m: String?
)