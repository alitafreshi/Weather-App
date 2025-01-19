package com.tafreshiali.weatherapp.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class HourlyForecastingDetail(
    @SerialName("apparent_temperature")
    val apparentTemperature: List<Double?>?,
    @SerialName("precipitation_probability")
    val precipitationProbability: List<Int?>?,
    @SerialName("relative_humidity_2m")
    val relativeHumidity2m: List<Int?>?,
    @SerialName("temperature_2m")
    val temperature2m: List<Double?>?,
    @SerialName("time")
    val time: List<String?>?,
    @SerialName("weather_code")
    val weatherCode: List<Int?>?,
    @SerialName("wind_speed_180m")
    val windSpeed180m: List<Double?>?
)