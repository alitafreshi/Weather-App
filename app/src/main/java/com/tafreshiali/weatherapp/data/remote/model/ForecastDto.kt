package com.tafreshiali.weatherapp.data.remote.model

import androidx.annotation.Keep
import com.tafreshiali.weatherapp.domain.model.HourlyWeatherForecasting
import com.tafreshiali.weatherapp.domain.model.NextWeekWeatherForecastingDetail
import com.tafreshiali.weatherapp.domain.model.TemperatureUnit
import com.tafreshiali.weatherapp.domain.model.WindSpeedUnit
import com.tafreshiali.weatherapp.domain.utils.calculateNextWeek
import com.tafreshiali.weatherapp.domain.utils.convertServerDateStringToDateFormat
import com.tafreshiali.weatherapp.domain.utils.generateNext24HourLaterTime
import com.tafreshiali.weatherapp.domain.utils.isDateInNextWeek
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

@Keep
@Serializable
data class ForecastDto(
    @SerialName("forecastday")
    val forecastDaysWeatherDetails: List<ForecastDayWeatherDetailDto>?
)

fun ForecastDto.toNext24HourHourlyWeatherForecastingList(): List<HourlyWeatherForecasting>? {
    val next24HourTime = generateNext24HourLaterTime()
    val forecastDetail = forecastDaysWeatherDetails?.flatMap { detailDto ->
        detailDto.hourlyForecastingDetail?.toHourlyWeatherForecastingList(next24HourTime)
            ?: emptyList()
    }
    return forecastDetail
}

fun ForecastDto.toNextWeekWeatherForecastingList(): List<NextWeekWeatherForecastingDetail>? {
    val nextWeek = calculateNextWeek()
    val forecastDetail = forecastDaysWeatherDetails?.mapNotNull { detailDto ->
        val serverDateString = detailDto.date ?: return@mapNotNull null
        val date =
            convertServerDateStringToDateFormat(serverDateString) ?: return@mapNotNull null
        val isDateInNextWeek =
            isDateInNextWeek(nextWeek = nextWeek, date = date)
        if (isDateInNextWeek) {
            return@mapNotNull NextWeekWeatherForecastingDetail(
                dayTitle = date.dayOfWeek.toString().lowercase()
                    .replaceFirstChar { it.uppercase() },
                temperature = detailDto.dayWeatherDetailDto?.averageTemperatureCelsius?.roundToInt()
                    ?.toString() ?: return@mapNotNull null,
                temperatureUnit = TemperatureUnit.Celsius,
                weatherCondition = detailDto.dayWeatherDetailDto.airConditionDto?.toWeatherCondition()
                    ?: return@mapNotNull null,
                windSpeed = detailDto.dayWeatherDetailDto.maximumWindSpeedKph?.roundToInt()
                    ?.toString() ?: return@mapNotNull null,
                windSpeedUnit = WindSpeedUnit.KilometerPerHour,
                humidity = detailDto.dayWeatherDetailDto.averageHumidity?.roundToInt()?.toString()
                    ?: return@mapNotNull null,
                rainFallInMillimeter = detailDto.dayWeatherDetailDto.dailyChanceOfRain
                    ?: return@mapNotNull null
            )
        }
        return@mapNotNull null
    }
    return forecastDetail
}
