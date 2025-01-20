package com.tafreshiali.weatherapp.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class WeatherApiDto(
    @SerialName("current")
    val currentWeatherDto: CurrentWeatherDto?,
    @SerialName("forecast")
    val forecastDto: ForecastDto?,
    @SerialName("location")
    val locationDetailDto: LocationDetailDto?
)