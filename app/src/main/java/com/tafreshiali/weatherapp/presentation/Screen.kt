package com.tafreshiali.weatherapp.presentation

sealed interface Screen {
    data object CurrentWeatherForecasting : Screen
    data object NextWeekWeatherForecasting : Screen
}