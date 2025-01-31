package com.tafreshiali.weatherapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.tafreshiali.weatherapp.presentation.current_weather.CurrentWeatherForecasting
import com.tafreshiali.weatherapp.presentation.current_weather.currentWeatherForecastingDestination
import com.tafreshiali.weatherapp.presentation.next_week_weather.NextWeekWeatherNavigation
import com.tafreshiali.weatherapp.presentation.next_week_weather.nextWeekWeatherDestination
import kotlinx.serialization.Serializable

@Serializable
data object WeatherNestedGraph

private fun NavGraphBuilder.weatherNestedGraph(navController: NavHostController) {
    navigation<WeatherNestedGraph>(startDestination = CurrentWeatherForecasting) {
        currentWeatherForecastingDestination(next7DaysWeatherForecastingDetailCallback = {
            navController.navigate(NextWeekWeatherNavigation)
        })
        nextWeekWeatherDestination()
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = WeatherNestedGraph
    ) {
        weatherNestedGraph(navController = navController)
    }
}