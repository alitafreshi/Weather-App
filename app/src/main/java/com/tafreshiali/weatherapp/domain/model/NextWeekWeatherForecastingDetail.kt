package com.tafreshiali.weatherapp.domain.model

data class NextWeekWeatherForecastingDetail(
    val dayTitle: String,
    val temperature: String,
    val temperatureUnit: TemperatureUnit,
    val weatherCondition: WeatherCondition,
    val windSpeed: String,
    val windSpeedUnit: WindSpeedUnit,
    val humidity: String,
    val rainFallInMillimeter: Int,
)