package com.tafreshiali.weatherapp.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep
import com.tafreshiali.weatherapp.domain.model.WeatherCondition
import com.tafreshiali.weatherapp.domain.model.WeatherConditionType

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

fun AirConditionDto.toWeatherCondition(): WeatherCondition? {
    return WeatherCondition(
        conditionName = name ?: return null,
        conditionIconUrl = if(iconUrl.isNullOrEmpty()) return null else "https:$iconUrl",
        conditionType = WeatherConditionType.fromCode(code ?: return null) ?: return null
    )
}