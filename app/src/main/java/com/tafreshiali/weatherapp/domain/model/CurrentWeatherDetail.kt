package com.tafreshiali.weatherapp.domain.model

data class CurrentWeatherDetail(
    val locationName: String,
    val dateTime: String,
    val temperature: String,
    val temperatureUnit: TemperatureUnit,
    val weatherCondition: WeatherCondition,
    val rainFallInMillimeter: Int,
    val humidity: Int,
    val isDay: Boolean,
    val windSpeed: Int,
    val windSpeedUnit: WindSpeedUnit,
    val hourlyWeatherForecastingUntilNextDay: List<HourlyWeatherForecasting>
)
