package com.tafreshiali.weatherapp.data.remote.model

import android.util.Log
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep
import androidx.compose.runtime.currentCompositionErrors
import androidx.compose.ui.util.fastRoundToInt
import com.tafreshiali.weatherapp.domain.model.CurrentWeatherDetail
import com.tafreshiali.weatherapp.domain.model.TemperatureUnit
import com.tafreshiali.weatherapp.domain.model.WindSpeedUnit
import com.tafreshiali.weatherapp.domain.utils.convertLocalDateTimeToHumanReadableFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Locale
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.roundToInt

@Keep
@Serializable
data class WeatherApiDto(
    @SerialName("current")
    val currentWeatherDto: CurrentWeatherDto?,
    @SerialName("forecast")
    val forecastDto: ForecastDto?,
    @SerialName("location")
    val locationDetailDto: LocationDetailDto?
)

fun WeatherApiDto.toCurrentWeatherDetail(): CurrentWeatherDetail? {
    val currentWeatherDto = currentWeatherDto ?: return null
    val locationDetailDto = locationDetailDto ?: return null
    val forecastDto = forecastDto ?: return null
    val readableDateTime =
        locationDetailDto.localDateTime?.convertLocalDateTimeToHumanReadableFormat()
    if (
        locationDetailDto.cityName.isNullOrEmpty() ||
        locationDetailDto.countryName.isNullOrEmpty() ||
        readableDateTime.isNullOrEmpty()
    ) {
        return null
    }

    return CurrentWeatherDetail(
        locationName = "${locationDetailDto.cityName},\n${locationDetailDto.countryName}",
        dateTime = readableDateTime,
        //TODO Later On Should Read Unit from the data store and Decied which unit to show
        temperature = (currentWeatherDto.temperatureCelsius?.roundToInt() ?: return null).toString(),
        temperatureUnit = TemperatureUnit.Celsius,
        weatherCondition = currentWeatherDto.airCondition?.toWeatherCondition() ?: return null,
        rainFallInMillimeter = currentWeatherDto.rainInMillimeter ?: return null,
        humidity = currentWeatherDto.humidity ?: return null,
        isDay = currentWeatherDto.isDay == 1,
        windSpeed = currentWeatherDto.windSpeedKph ?: return null,
        //TODO Later On Should Read Unit from the data store and Decied which unit to show
        windSpeedUnit = WindSpeedUnit.KilometerPerHour,
        hourlyWeatherForecastingUntilNextDay = forecastDto.toNext24HourHourlyWeatherForecastingList()
            ?: return null
    )
}
