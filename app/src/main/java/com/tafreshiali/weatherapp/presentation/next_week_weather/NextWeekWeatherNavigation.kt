package com.tafreshiali.weatherapp.presentation.next_week_weather

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tafreshiali.weatherapp.presentation.WeatherNestedGraph
import com.tafreshiali.weatherapp.presentation.WeatherViewModel
import com.tafreshiali.weatherapp.presentation.sharedViewModel
import kotlinx.serialization.Serializable

@Serializable
data object NextWeekWeatherNavigation

fun NavGraphBuilder.nextWeekWeatherDestination(navController: NavController) {
    composable<NextWeekWeatherNavigation> { backStackEntry ->
        val weatherViewModel =
            backStackEntry.sharedViewModel<WeatherViewModel, WeatherNestedGraph>(navController)
        NextWeekWeatherForecastingScreen(
            weatherViewModel = weatherViewModel,
            onClickBack = {
                navController.navigateUp()
            }
        )
    }
}