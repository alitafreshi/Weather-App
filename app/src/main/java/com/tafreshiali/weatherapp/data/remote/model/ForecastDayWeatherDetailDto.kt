package com.tafreshiali.weatherapp.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class ForecastDayWeatherDetailDto(
    @SerialName("astro")
    val astroDto: AstroDto?,
    @SerialName("date")
    val date: String?,
    @SerialName("day")
    val dayWeatherDetailDto: DayWeatherDetailDto?,
    @SerialName("hour")
    val hourlyForecastingDetail: List<HourlyForecastingDetailDto>?
)