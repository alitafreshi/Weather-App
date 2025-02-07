package com.tafreshiali.weatherapp.presentation.next_week_weather

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.tafreshiali.weatherapp.presentation.WeatherViewModel
import com.tafreshiali.weatherapp.presentation.next_week_weather.components.NextWeekForecastingExpandableItemComponent
import com.tafreshiali.weatherapp.presentation.next_week_weather.components.NextWeekWeatherForecastingTopBar
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme

@Composable
fun NextWeekWeatherForecastingScreen(
    weatherViewModel: WeatherViewModel,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit
) {
    val weatherViewState = weatherViewModel.nextWeekWeatherForecastingViewState.collectAsState()

    val colorStops = arrayOf(
        0.0f to WeatherAppTheme.colorScheme.primaryLight,
        1f to WeatherAppTheme.colorScheme.primaryDark
    )
    val brush = Brush.linearGradient(colorStops = colorStops)
    val weatherData = weatherViewState.value.nextWeekWeatherForecastData
    var expandedItem by remember { mutableIntStateOf(-1) }
    Log.d("WEATHER_APP", "NextWeekWeatherForecastingScreenData is $weatherData ")
    if (weatherData != null) {
        Scaffold(
            topBar = {
                NextWeekWeatherForecastingTopBar(onClickBack = onClickBack)
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
                    items(weatherData, key = { it.id }) {
                        NextWeekForecastingExpandableItemComponent(
                            weekDayWeatherForecastingDetail = it,
                            expanded = expandedItem == it.id,
                            onClickExpanded = { id ->
                                expandedItem = if (expandedItem == id) -1 else id
                            }
                        )
                    }
                }
            }
        }
    }
}
