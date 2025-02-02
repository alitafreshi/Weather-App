package com.tafreshiali.weatherapp.presentation.current_weather

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tafreshiali.weatherapp.presentation.WeatherNestedGraph
import com.tafreshiali.weatherapp.presentation.WeatherViewModel
import com.tafreshiali.weatherapp.presentation.next_week_weather.NextWeekWeatherNavigation
import com.tafreshiali.weatherapp.presentation.sharedViewModel
import kotlinx.serialization.Serializable

@Serializable
data object CurrentWeatherForecasting

fun NavGraphBuilder.currentWeatherForecastingDestination(navController: NavController) {
    composable<CurrentWeatherForecasting> { backStackEntry ->
        val weatherViewModel = backStackEntry.sharedViewModel<WeatherViewModel, WeatherNestedGraph>(navController)
        CurrentWeatherForecastingScreen(
            weatherViewModel = weatherViewModel,
            next7DaysWeatherForecastingDetailCallback = {
                weatherViewModel.getNextWeekWeatherForecastDataByCityName("Tehran")
                navController.navigate(NextWeekWeatherNavigation)
            }
        )
    }
}