package com.tafreshiali.weatherapp.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class DayWeatherDetailDto(
    @SerialName("avghumidity")
    val averageHumidity: Double?,
    @SerialName("avgtemp_c")
    val averageTemperatureCelsius: Double?,
    @SerialName("avgtemp_f")
    val averageTemperatureFahrenheit: Double?,
    @SerialName("avgvis_km")
    val averageVisibilityKilometer: Double?,
    @SerialName("avgvis_miles")
    val averageVisibilityMiles: Double?,
    @SerialName("condition")
    val airConditionDto: AirConditionDto?,
    @SerialName("daily_chance_of_rain")
    val dailyChanceOfRain: Int?,
    @SerialName("daily_chance_of_snow")
    val dailyChanceOfSnow: Double?,
    @SerialName("daily_will_it_rain")
    val dailyWillItRain: Double?,
    @SerialName("daily_will_it_snow")
    val dailyWillItSnow: Double?,
    @SerialName("maxtemp_c")
    val maximumTemperatureCelsius: Double?,
    @SerialName("maxtemp_f")
    val maximumTemperatureFahrenheit: Double?,
    @SerialName("maxwind_kph")
    val maximumWindSpeedKph: Double?,
    @SerialName("maxwind_mph")
    val maximumWindSpeedMph: Double?,
    @SerialName("mintemp_c")
    val minimumTemperatureCelsius: Double?,
    @SerialName("mintemp_f")
    val minimumTemperatureFahrenheit: Double?,
    @SerialName("uv")
    val uv: Double?
)