package com.tafreshiali.weatherapp.data.remote.model

import androidx.annotation.Keep
import com.tafreshiali.weatherapp.domain.model.HourlyWeatherForecasting
import com.tafreshiali.weatherapp.domain.utils.generateNext24HourLaterTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ForecastDto(
    @SerialName("forecastday")
    val forecastDaysWeatherDetails: List<ForecastDayWeatherDetailDto>?
)

fun ForecastDto.toNext24HourHourlyWeatherForecastingList(): List<HourlyWeatherForecasting>? {
    val next24HourTime = generateNext24HourLaterTime()
    val forecastDetail = forecastDaysWeatherDetails?.flatMap { detailDto ->
        detailDto.hourlyForecastingDetail?.toHourlyWeatherForecastingList(next24HourTime) ?: emptyList()
    }
    return forecastDetail
}
