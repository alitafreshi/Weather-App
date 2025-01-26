package com.tafreshiali.weatherapp.presentation.current_weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.tafreshiali.weatherapp.R
import com.tafreshiali.weatherapp.presentation.WeatherViewModel
import com.tafreshiali.weatherapp.presentation.current_weather.components.CurrentLocationWeatherForecastingPagerComponent
import com.tafreshiali.weatherapp.presentation.current_weather.components.CurrentWeatherForecastingTopBar
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme

@Composable
fun CurrentWeatherForecastingScreen(
    weatherViewModel: WeatherViewModel,
    modifier: Modifier = Modifier
) {
    val weatherViewState = weatherViewModel.weatherViewState.collectAsState()
    //TODO the initialPage and pageCount should be dynamic based on the added locations in dataStore
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    val colorStops = arrayOf(
        0.0f to WeatherAppTheme.colorScheme.primaryLight,
        1f to WeatherAppTheme.colorScheme.primaryDark
    )
    val brush = Brush.linearGradient(colorStops = colorStops)
    Scaffold(
        topBar = {
            CurrentWeatherForecastingTopBar(pagerState = pagerState)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(top = 20.dp)
            ) {
                item {
                    val weatherData = weatherViewState.value.weatherData
                    if (weatherData != null) {
                        CurrentLocationWeatherForecastingPagerComponent(
                            pagerState = pagerState,
                            cityName = weatherData.locationName,
                            dateTime = weatherData.dateTime,
                            temperature = weatherData.temperature,
                            temperatureUnit = weatherData.temperatureUnit.unit,
                            weatherStatus = weatherData.weatherCondition.conditionName,
                            weatherLocalIcon = R.drawable.ic_sun_and_cloud,
                            weatherIconUrl = weatherData.weatherCondition.conditionIconUrl
                        )
                    }
                }
            }
        }
    }
}