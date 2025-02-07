package com.tafreshiali.weatherapp.presentation.current_weather

import androidx.core.splashscreen.SplashScreen
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

fun NavGraphBuilder.currentWeatherForecastingDestination(
    navController: NavController,
    splashScreen: SplashScreen
) {
    composable<CurrentWeatherForecasting> { backStackEntry ->
        val weatherViewModel = backStackEntry.sharedViewModel<WeatherViewModel, WeatherNestedGraph>(navController)
        splashScreen.setKeepOnScreenCondition { weatherViewModel.weatherViewState.value.loadingState }
        CurrentWeatherForecastingScreen(
            weatherViewModel = weatherViewModel,
            next7DaysWeatherForecastingDetailCallback = {
                weatherViewModel.getNextWeekWeatherForecastDataByCityName("Tehran")
                navController.navigate(NextWeekWeatherNavigation)
            }
        )
    }
}