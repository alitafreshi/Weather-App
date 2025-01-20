package com.tafreshiali.weatherapp.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class CurrentWeatherDto(
    @SerialName("cloud")
    val cloud: Int?,
    @SerialName("condition")
    val airCondition: AirConditionDto?,
    @SerialName("feelslike_c")
    val feelsLikeTemperatureCelsius: Double?,
    @SerialName("feelslike_f")
    val feelsLikeTemperatureFahrenheit: Double?,
    @SerialName("heatindex_c")
    val heatIndexCelsius: Double?,
    @SerialName("heatindex_f")
    val heatIndexFahrenheit: Double?,
    @SerialName("humidity")
    val humidity: Int?,
    @SerialName("is_day")
    val isDay: Int?,
    @SerialName("last_updated")
    val lastUpdated: String?,
    @SerialName("temp_c")
    val temperatureCelsius: Double?,
    @SerialName("temp_f")
    val temperatureFahrenheit: Int?,
    @SerialName("uv")
    val uv: Int?,
    @SerialName("vis_km")
    val visibilityKilometer: Int?,
    @SerialName("vis_miles")
    val visibilityMiles: Int?,
    @SerialName("wind_kph")
    val windSpeedKph: Double?,
    @SerialName("wind_mph")
    val windSpeedMph: Double?,
)