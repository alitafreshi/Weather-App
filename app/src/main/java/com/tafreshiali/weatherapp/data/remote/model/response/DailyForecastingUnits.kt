package com.tafreshiali.weatherapp.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class DailyForecastingUnits(
    @SerialName("sunrise")
    val sunrise: String?,
    @SerialName("sunset")
    val sunset: String?,
    @SerialName("time")
    val time: String?,
    @SerialName("weather_code")
    val weatherCode: String?
)