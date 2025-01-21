package com.tafreshiali.weatherapp.data.remote.model

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ErrorDto(
    @SerialName("code")
    val code: Int?,
    @SerialName("message")
    val message: String?
)