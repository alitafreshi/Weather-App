package com.tafreshiali.weatherapp.presentation.current_weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tafreshiali.weatherapp.presentation.WeatherViewModel

@Composable
fun CurrentWeatherForecastingScreen(
    weatherViewModel: WeatherViewModel,
    modifier: Modifier = Modifier
) {
    val weatherViewState = weatherViewModel.weatherViewState.collectAsState()
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "CURRENT WEATHER FORECASTING", modifier = Modifier.fillMaxWidth())
    }
}