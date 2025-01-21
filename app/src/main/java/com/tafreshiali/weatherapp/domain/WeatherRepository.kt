package com.tafreshiali.weatherapp.domain

import com.tafreshiali.weatherapp.data.remote.model.WeatherApiDto
import com.tafreshiali.weatherapp.data.state.DataState
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherForecastingByLocation(cityName: String): Flow<DataState<WeatherApiDto>>
}