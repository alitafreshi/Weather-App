package com.tafreshiali.weatherapp.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class AstroDto(
    @SerialName("is_moon_up")
    val isMoonUp: Int?,
    @SerialName("is_sun_up")
    val isSunUp: Int?,
    @SerialName("moon_illumination")
    val moonIllumination: Int?,
    @SerialName("moon_phase")
    val moonPhase: String?,
    @SerialName("moonrise")
    val moonrise: String?,
    @SerialName("moonset")
    val moonSet: String?,
    @SerialName("sunrise")
    val sunrise: String?,
    @SerialName("sunset")
    val sunset: String?
)