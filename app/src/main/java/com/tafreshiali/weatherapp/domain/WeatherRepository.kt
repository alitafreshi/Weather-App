package com.tafreshiali.weatherapp.domain

import com.tafreshiali.weatherapp.data.state.DataState
import com.tafreshiali.weatherapp.domain.model.CurrentWeatherDetail
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherForecastingByLocation(cityName: String): Flow<DataState<CurrentWeatherDetail>>
}