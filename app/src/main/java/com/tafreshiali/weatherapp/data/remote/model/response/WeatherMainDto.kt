package com.tafreshiali.weatherapp.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class WeatherMainDto(
    @SerialName("current")
    val currentDayDetail: CurrentDayDetail?,
    @SerialName("current_units")
    val currentDayUnits: CurrentDayUnits?,
    @SerialName("daily")
    val dailyForecastingDetail: DailyForecastingDetail?,
    @SerialName("daily_units")
    val dailyForecastingUnits: DailyForecastingUnits?,
    @SerialName("hourly")
    val hourlyForecastingDetail: HourlyForecastingDetail?,
    @SerialName("hourly_units")
    val hourlyForecastingUnits: HourlyForecastingUnits?,
    @SerialName("latitude")
    val latitude: Double?,
    @SerialName("longitude")
    val longitude: Double?,
    @SerialName("timezone")
    val locationName: String?,
)