package com.tafreshiali.weatherapp.presentation.next_week_weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tafreshiali.weatherapp.presentation.WeatherViewModel
import kotlinx.serialization.Serializable

@Serializable
data object NextWeekWeatherNavigation

fun NavGraphBuilder.nextWeekWeatherDestination() {
    composable<NextWeekWeatherNavigation> { backStackEntry ->
        val weatherViewModel = hiltViewModel<WeatherViewModel>(backStackEntry)
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "NEXT WEEK WEATHER FORECASTING SCREEN")
        }
    }
}