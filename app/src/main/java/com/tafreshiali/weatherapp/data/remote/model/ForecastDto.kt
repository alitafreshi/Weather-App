package com.tafreshiali.weatherapp.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep
import com.tafreshiali.weatherapp.domain.model.HourlyWeatherForecasting

@Keep
@Serializable
data class ForecastDto(
    @SerialName("forecastday")
    val forecastDaysWeatherDetails: List<ForecastDayWeatherDetailDto>?
)

fun ForecastDto.toNext24HourHourlyWeatherForecastingList(): List<HourlyWeatherForecasting>? =
    forecastDaysWeatherDetails?.flatMap { detailDto ->
        detailDto.hourlyForecastingDetail?.toHourlyWeatherForecastingList() ?: emptyList()
    }