package com.tafreshiali.weatherapp.presentation.viewstate

import com.tafreshiali.weatherapp.data.remote.model.WeatherApiDto

data class WeatherViewState(
    val loadingState: Boolean = false,
    val errorState: Boolean = false,
    val weatherData: WeatherApiDto? = null
)
