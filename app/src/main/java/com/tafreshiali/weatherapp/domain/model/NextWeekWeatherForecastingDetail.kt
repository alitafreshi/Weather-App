package com.tafreshiali.weatherapp.domain.model

import org.threeten.bp.LocalDate

data class NextWeekWeatherForecastingDetail(
    val id: Int,
    val date: LocalDate,
    val dayTitle: String,
    val temperature: String,
    val temperatureUnit: TemperatureUnit,
    val weatherCondition: WeatherCondition,
    val windSpeed: String,
    val windSpeedUnit: WindSpeedUnit,
    val humidity: String,
    val rainFallInMillimeter: Int,
)