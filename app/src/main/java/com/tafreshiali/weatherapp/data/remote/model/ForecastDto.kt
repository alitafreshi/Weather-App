package com.tafreshiali.weatherapp.data.remote.model

import android.util.Log
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
    val forecastDetail = forecastDaysWeatherDetails?.mapIndexedNotNull { index, detailDto ->
        val serverDateString = detailDto.date ?: return@mapIndexedNotNull null
        val date =
            convertServerDateStringToDateFormat(serverDateString) ?: return@mapIndexedNotNull null
        val isDateInNextWeek =
            isDateInNextWeek(nextWeek = nextWeek, date = date)
        if (isDateInNextWeek) {
            return@mapIndexedNotNull NextWeekWeatherForecastingDetail(
                id = index,
                date = date,
                dayTitle = date.dayOfWeek.toString().lowercase()
                    .replaceFirstChar { it.uppercase() },
                temperature = detailDto.dayWeatherDetailDto?.averageTemperatureCelsius?.roundToInt()
                    ?.toString() ?: return@mapIndexedNotNull null,
                temperatureUnit = TemperatureUnit.Celsius,
                weatherCondition = detailDto.dayWeatherDetailDto.airConditionDto?.toWeatherCondition()
                    ?: return@mapIndexedNotNull null,
                windSpeed = detailDto.dayWeatherDetailDto.maximumWindSpeedKph?.roundToInt()
                    ?.toString() ?: return@mapIndexedNotNull null,
                windSpeedUnit = WindSpeedUnit.KilometerPerHour,
                humidity = detailDto.dayWeatherDetailDto.averageHumidity?.roundToInt()?.toString()
                    ?: return@mapIndexedNotNull null,
                rainFallInMillimeter = detailDto.dayWeatherDetailDto.dailyChanceOfRain
                    ?: return@mapIndexedNotNull null
            )
        }
        return@mapIndexedNotNull null
    }
    return forecastDetail
}
