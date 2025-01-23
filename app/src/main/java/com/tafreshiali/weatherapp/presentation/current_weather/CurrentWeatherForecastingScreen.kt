package com.tafreshiali.weatherapp.presentation.current_weather

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.tafreshiali.weatherapp.presentation.WeatherViewModel
import com.tafreshiali.weatherapp.presentation.current_weather.components.CurrentWeatherForecastingTopBar

@Composable
fun CurrentWeatherForecastingScreen(
    weatherViewModel: WeatherViewModel,
    modifier: Modifier = Modifier
) {
    val weatherViewState = weatherViewModel.weatherViewState.collectAsState()
    //TODO the initialPage and pageCount should be dynamic based on the added locations in dataStore
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    Scaffold(
        topBar = {
            CurrentWeatherForecastingTopBar(pagerState = pagerState)
        }
    ) { innerPadding ->

    }
}