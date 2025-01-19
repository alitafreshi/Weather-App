package com.tafreshiali.weatherapp.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class CurrentDayDetail(
    @SerialName("interval")
    val interval: Int?,
    @SerialName("is_day")
    val isDay: Int?,
    @SerialName("time")
    val time: String?,
    @SerialName("wind_speed_10m")
    val windSpeed10m: Double?
)