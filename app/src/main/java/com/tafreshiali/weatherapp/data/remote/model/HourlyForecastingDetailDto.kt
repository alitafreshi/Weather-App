package com.tafreshiali.weatherapp.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class HourlyForecastingDetailDto(
    @SerialName("chance_of_rain")
    val chanceOfRain: Int?,
    @SerialName("chance_of_snow")
    val chanceOfSnow: Int?,
    @SerialName("cloud")
    val cloud: Int?,
    @SerialName("condition")
    val airConditionDto: AirConditionDto?,
    @SerialName("feelslike_c")
    val feelsLikeTemperatureCelsius: Double?,
    @SerialName("feelslike_f")
    val feelsLikeTemperatureFahrenheit: Double?,
    @SerialName("humidity")
    val humidity: Int?,
    @SerialName("is_day")
    val isDay: Int?,
    @SerialName("snow_cm")
    val snowCm: Int?,
    @SerialName("temp_c")
    val temperatureCelsius: Double?,
    @SerialName("temp_f")
    val temperatureFahrenheit: Double?,
    @SerialName("time")
    val time: String?,
    @SerialName("uv")
    val uv: Double?,
    @SerialName("vis_km")
    val visibilityKilometer: Int?,
    @SerialName("vis_miles")
    val visibilityMiles: Int?,
    @SerialName("will_it_rain")
    val willItRain: Int?,
    @SerialName("will_it_snow")
    val willItSnow: Int?,
    @SerialName("wind_kph")
    val windSpeedKph: Double?,
    @SerialName("wind_mph")
    val windSpeedMph: Double?,
)