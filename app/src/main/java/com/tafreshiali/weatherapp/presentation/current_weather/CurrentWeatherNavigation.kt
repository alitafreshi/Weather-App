package com.tafreshiali.weatherapp.presentation.current_weather

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tafreshiali.weatherapp.presentation.WeatherViewModel
import kotlinx.serialization.Serializable

@Serializable
data object CurrentWeatherForecasting

fun NavGraphBuilder.currentWeatherForecastingDestination() {
    composable<CurrentWeatherForecasting> { backStackEntry ->
        val weatherViewModel = hiltViewModel<WeatherViewModel>(backStackEntry)
        CurrentWeatherForecastingScreen(weatherViewModel = weatherViewModel)
    }
}