package com.tafreshiali.weatherapp.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class AirConditionDto(
    @SerialName("code")
    val code: Int?,
    @SerialName("icon")
    val iconUrl: String?,
    @SerialName("text")
    val name: String?
)