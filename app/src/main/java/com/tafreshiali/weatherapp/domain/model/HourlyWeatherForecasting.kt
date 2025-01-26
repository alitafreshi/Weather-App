package com.tafreshiali.weatherapp.domain.model

data class HourlyWeatherForecasting(
    val time: String,
    val temperature: String,
    val weatherCondition: WeatherCondition
)
