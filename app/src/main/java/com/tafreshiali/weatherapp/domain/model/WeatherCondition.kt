package com.tafreshiali.weatherapp.domain.model

data class WeatherCondition(
    val conditionName: String,
    val conditionIconUrl: String,
    val conditionType: WeatherConditionType
)