package com.tafreshiali.weatherapp.domain.model

data class CurrentWeatherDetail(
    val locationName: String,
    val dateTime: String,
    val temperature: String,
    val temperatureUnit: TemperatureUnit,
    val weatherCondition: WeatherCondition,
    val rainFallInMillimeter: Double,
    val humidity: Double,
    val isDay: Boolean,
    val windSpeed: Double,
    val windSpeedUnit: WindSpeedUnit,
    val hourlyWeatherForecastingUntilNextDay: List<HourlyWeatherForecasting>
)
