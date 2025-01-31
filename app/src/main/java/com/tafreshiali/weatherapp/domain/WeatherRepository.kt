package com.tafreshiali.weatherapp.domain

import com.tafreshiali.weatherapp.data.state.DataState
import com.tafreshiali.weatherapp.domain.model.CurrentWeatherDetail
import com.tafreshiali.weatherapp.domain.model.NextWeekWeatherForecastingDetail
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherForecastingByLocation(cityName: String): Flow<DataState<CurrentWeatherDetail>>
    fun getNextWeekWeatherForecastingByLocation(cityName: String): Flow<DataState<List<NextWeekWeatherForecastingDetail>>>
}