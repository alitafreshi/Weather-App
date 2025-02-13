package com.tafreshiali.weatherapp.presentation.current_weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tafreshiali.weatherapp.R
import com.tafreshiali.weatherapp.presentation.WeatherViewModel
import com.tafreshiali.weatherapp.presentation.components.ErrorStateComponent
import com.tafreshiali.weatherapp.presentation.current_weather.components.CurrentLocationWeatherForecastingPagerComponent
import com.tafreshiali.weatherapp.presentation.current_weather.components.CurrentWeatherForecastingTopBar
import com.tafreshiali.weatherapp.presentation.current_weather.components.Next24HourWeatherForecastingDetailComponent
import com.tafreshiali.weatherapp.presentation.current_weather.components.Next24HourWeatherForecastingHeaderComponent
import com.tafreshiali.weatherapp.presentation.current_weather.components.WeatherConditionInfoComponent
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherForecastingScreen(
    weatherViewModel: WeatherViewModel,
    modifier: Modifier = Modifier,
    next7DaysWeatherForecastingDetailCallback: () -> Unit
) {
    val weatherViewState = weatherViewModel.weatherViewState.collectAsStateWithLifecycle()
    //TODO the initialPage and pageCount should be dynamic based on the added locations in dataStore
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    val colorStops = arrayOf(
        0.0f to WeatherAppTheme.colorScheme.primaryLight,
        1f to WeatherAppTheme.colorScheme.primaryDark
    )
    val brush = Brush.linearGradient(colorStops = colorStops)
    val weatherData = weatherViewState.value.weatherData
    Scaffold(
        topBar = {
            CurrentWeatherForecastingTopBar(pagerState = pagerState)
        }
    ) { innerPadding ->
        PullToRefreshBox(
            state = rememberPullToRefreshState(),
            isRefreshing = weatherViewState.value.loadingRefreshState,
            onRefresh = {
                weatherViewModel.getWeatherForecastDataByCityName(
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
                contentPadding = PaddingValues(bottom = 10.dp)
            ) {
                when {
                    /*weatherViewState.value.loadingState && !weatherViewState.value.loadingRefreshState -> {
                        item {
                            LoadingStateComponent()
                        }
                    }*/

                    weatherViewState.value.errorState || (weatherViewState.value.loadingRefreshState && weatherData == null) -> {
                        item {
                            ErrorStateComponent()
                        }
                    }

                    weatherData != null -> {
                        item {
                            CurrentLocationWeatherForecastingPagerComponent(
                                modifier = Modifier.padding(horizontal = 15.dp),
                                pagerState = pagerState,
                                cityName = weatherData.locationName,
                                dateTime = weatherData.dateTime,
                                temperature = weatherData.temperature,
                                temperatureUnit = weatherData.temperatureUnit.unit,
                                weatherStatus = weatherData.weatherCondition.conditionName,
                                weatherIcon = R.drawable.ic_sun_and_cloud,
                            )

                        }

                        item {
                            WeatherConditionInfoComponent(
                                modifier = Modifier.padding(horizontal = 15.dp),
                                conditionTitle = stringResource(
                                    R.string.weather_condition_tv_rain_fall_title
                                ),
                                conditionValue = stringResource(
                                    R.string.weather_condition_tv_rain_fall_value_in_centimeter,
                                    weatherData.rainFallInMillimeter
                                ),
                                conditionIcon = R.drawable.ic_rain_fall,
                            )
                        }

                        item {
                            WeatherConditionInfoComponent(
                                modifier = Modifier.padding(horizontal = 15.dp),
                                conditionTitle = stringResource(
                                    R.string.weather_condition_tv_wind_title
                                ),
                                conditionValue = stringResource(
                                    R.string.weather_condition_tv_wind_value_in_kilometer_per_hour,
                                    weatherData.windSpeed
                                ),
                                conditionIcon = R.drawable.ic_wind,
                            )
                        }

                        item {
                            WeatherConditionInfoComponent(
                                modifier = Modifier.padding(horizontal = 15.dp),
                                conditionTitle = stringResource(
                                    R.string.weather_condition_tv_humidity_title
                                ),
                                conditionValue = "${weatherData.humidity}%",
                                conditionIcon = R.drawable.ic_humidity,
                            )
                        }

                        item {
                            Next24HourWeatherForecastingHeaderComponent(
                                modifier = Modifier.padding(horizontal = 15.dp),
                                nextWeekWeatherForecastingDetailCallback = next7DaysWeatherForecastingDetailCallback
                            )
                        }

                        item {
                            Next24HourWeatherForecastingDetailComponent(
                                next24HourWeatherForecastingData = weatherData.hourlyWeatherForecastingUntilNextDay
                            )
                        }
                    }
                }
            }

        }
    }
}