package com.tafreshiali.weatherapp.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class HourlyForecastingDetailDto(
    @SerialName("chance_of_rain")
    val chanceOfRain: Double?,
    @SerialName("chance_of_snow")
    val chanceOfSnow: Double?,
    @SerialName("cloud")
    val cloud: Double?,
    @SerialName("condition")
    val airConditionDto: AirConditionDto?,
    @SerialName("feelslike_c")
    val feelsLikeTemperatureCelsius: Double?,
    @SerialName("feelslike_f")
    val feelsLikeTemperatureFahrenheit: Double?,
    @SerialName("humidity")
    val humidity: Double?,
    @SerialName("is_day")
    val isDay: Int?,
    @SerialName("snow_cm")
    val snowCm: Double?,
    @SerialName("temp_c")
    val temperatureCelsius: Double?,
    @SerialName("temp_f")
    val temperatureFahrenheit: Double?,
    @SerialName("time")
    val time: String?,
    @SerialName("uv")
    val uv: Double?,
    @SerialName("vis_km")
    val visibilityKilometer: Double?,
    @SerialName("vis_miles")
    val visibilityMiles: Double?,
    @SerialName("will_it_rain")
    val willItRain: Int?,
    @SerialName("will_it_snow")
    val willItSnow: Int?,
    @SerialName("wind_kph")
    val windSpeedKph: Double?,
    @SerialName("wind_mph")
    val windSpeedMph: Double?,
)