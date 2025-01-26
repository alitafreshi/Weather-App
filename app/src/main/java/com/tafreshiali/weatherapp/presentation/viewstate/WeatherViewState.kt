package com.tafreshiali.weatherapp.presentation.viewstate

import com.tafreshiali.weatherapp.domain.model.CurrentWeatherDetail

data class WeatherViewState(
    val loadingState: Boolean = false,
    val errorState: Boolean = false,
    val weatherData: CurrentWeatherDetail? = null
)
