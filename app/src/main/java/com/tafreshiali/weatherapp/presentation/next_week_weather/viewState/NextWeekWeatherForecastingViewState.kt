package com.tafreshiali.weatherapp.presentation.next_week_weather.viewState

import com.tafreshiali.weatherapp.domain.model.NextWeekWeatherForecastingDetail

data class NextWeekWeatherForecastingViewState(
    val loadingState: Boolean = false,
    val loadingRefreshState: Boolean = false,
    val errorState: Boolean = false,
    val nextWeekWeatherForecastData: List<NextWeekWeatherForecastingDetail>? = null
)