package com.tafreshiali.weatherapp.presentation.next_week_weather

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tafreshiali.weatherapp.presentation.WeatherViewModel
import com.tafreshiali.weatherapp.presentation.components.ErrorStateComponent
import com.tafreshiali.weatherapp.presentation.next_week_weather.components.NextWeekForecastingExpandableItemComponent
import com.tafreshiali.weatherapp.presentation.next_week_weather.components.NextWeekWeatherForecastingTopBar
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NextWeekWeatherForecastingScreen(
    weatherViewModel: WeatherViewModel,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit
) {
    val weatherViewState =
        weatherViewModel.nextWeekWeatherForecastingViewState.collectAsStateWithLifecycle()

    val colorStops = arrayOf(
        0.0f to WeatherAppTheme.colorScheme.primaryLight,
        1f to WeatherAppTheme.colorScheme.primaryDark
    )
    val brush = Brush.linearGradient(colorStops = colorStops)
    val weatherData = weatherViewState.value.nextWeekWeatherForecastData
    var expandedItem by remember { mutableIntStateOf(-1) }
    Log.d("WEATHER_APP", "NextWeekWeatherForecastingScreenData is $weatherData ")
    Scaffold(
        topBar = {
            NextWeekWeatherForecastingTopBar(onClickBack = onClickBack)
        }) { innerPadding ->
        PullToRefreshBox(
            state = rememberPullToRefreshState(),
            isRefreshing = weatherViewState.value.loadingRefreshState,
            onRefresh = {
                weatherViewModel.getNextWeekWeatherForecastDataByCityName(
                    cityName = "Tehran",
                    retryState = true
                )
            },
            modifier = Modifier
                .fillMaxSize()
                .background(brush)
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
            ) {
                when {
                    weatherViewState.value.errorState || (weatherViewState.value.loadingRefreshState && weatherData == null) -> {
                        item {
                            ErrorStateComponent()
                        }
                    }

                    weatherData != null -> {
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
}
