package com.tafreshiali.weatherapp.presentation.next_week_weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.tafreshiali.weatherapp.presentation.WeatherViewModel
import com.tafreshiali.weatherapp.presentation.next_week_weather.components.NextWeekWeatherForecastingTopBar
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme

@Composable
fun NextWeekWeatherForecastingScreen(
    weatherViewModel: WeatherViewModel,
    modifier: Modifier = Modifier
) {
    val weatherViewState = weatherViewModel.weatherViewState.collectAsState()

    val colorStops = arrayOf(
        0.0f to WeatherAppTheme.colorScheme.primaryLight,
        1f to WeatherAppTheme.colorScheme.primaryDark
    )
    val brush = Brush.linearGradient(colorStops = colorStops)
    val weatherData = weatherViewState.value.weatherData
    Scaffold(
        topBar = {
            NextWeekWeatherForecastingTopBar()
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
            ) {

            }
        }
    }
}
