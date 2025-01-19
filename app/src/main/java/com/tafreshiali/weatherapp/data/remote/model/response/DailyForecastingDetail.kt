package com.tafreshiali.weatherapp.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class DailyForecastingDetail(
    @SerialName("sunrise")
    val sunrise: List<String?>?,
    @SerialName("sunset")
    val sunset: List<String?>?,
    @SerialName("time")
    val time: List<String?>?,
    @SerialName("weather_code")
    val weatherCode: List<Int?>?
)