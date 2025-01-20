package com.tafreshiali.weatherapp.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class ForecastDto(
    @SerialName("forecastday")
    val forecastDaysWeatherDetails: List<ForecastDayWeatherDetailDto>?
)