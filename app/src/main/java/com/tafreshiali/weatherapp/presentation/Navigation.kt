package com.tafreshiali.weatherapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.tafreshiali.weatherapp.presentation.current_weather.CurrentWeatherForecasting
import com.tafreshiali.weatherapp.presentation.current_weather.currentWeatherForecastingDestination
import com.tafreshiali.weatherapp.presentation.next_week_weather.nextWeekWeatherDestination
import kotlinx.serialization.Serializable

@Serializable
data object WeatherNestedGraph

private fun NavGraphBuilder.weatherNestedGraph(navController: NavHostController) {
    navigation<WeatherNestedGraph>(startDestination = CurrentWeatherForecasting) {
        currentWeatherForecastingDestination(navController)
        nextWeekWeatherDestination(navController)
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

@Composable
inline fun <reified VM : ViewModel, reified ScopeRoute : @Serializable Any> NavBackStackEntry.sharedViewModel(
    navController: NavController
): VM {
    val getParentEntryResult = runCatching {
        navController.getBackStackEntry<ScopeRoute>()
    }
    getParentEntryResult.fold(
        onSuccess = { parentEntry ->
            return hiltViewModel<VM>(parentEntry)
        },
        onFailure = { exception ->
            if (exception is IllegalArgumentException) {
                return viewModel<VM>()
            }
            throw exception
        }
    )
}
