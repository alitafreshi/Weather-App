package com.tafreshiali.weatherapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tafreshiali.weatherapp.data.state.DataState
import com.tafreshiali.weatherapp.domain.WeatherRepository
import com.tafreshiali.weatherapp.presentation.next_week_weather.viewState.NextWeekWeatherForecastingViewState
import com.tafreshiali.weatherapp.presentation.viewstate.WeatherViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private val _weatherViewState = MutableStateFlow(WeatherViewState())
    val weatherViewState = _weatherViewState.asStateFlow()

    private val _nextWeekWeatherForecastingViewState = MutableStateFlow(
        NextWeekWeatherForecastingViewState()
    )
    val nextWeekWeatherForecastingViewState = _nextWeekWeatherForecastingViewState.asStateFlow()

    init {
        getWeatherForecastDataByCityName("Tehran")
    }

    fun getWeatherForecastDataByCityName(cityName: String, retryState: Boolean = false) {
        weatherRepository.getWeatherForecastingByLocation(cityName = cityName)
            .onEach { dataState ->
                when (dataState) {
                    DataState.Loading -> _weatherViewState.update {
                        Log.d("WeatherViewModel", "CurrentState is Loading")
                        it.copy(
                            loadingState = !retryState,
                            loadingRefreshState = retryState,
                            errorState = false,
                        )
                    }

                    is DataState.Data -> {
                        _weatherViewState.update {
                            it.copy(
                                loadingState = false,
                                loadingRefreshState = false,
                                errorState = false,
                                weatherData = dataState.data
                            )
                        }
                    }

                    is DataState.Error -> _weatherViewState.update {
                        it.copy(
                            loadingState = false,
                            loadingRefreshState = false,
                            errorState = true,
                            weatherData = null
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun getNextWeekWeatherForecastDataByCityName(cityName: String, retryState: Boolean = false) {
        weatherRepository.getNextWeekWeatherForecastingByLocation(cityName = cityName)
            .onEach { dataState ->
                when (dataState) {
                    DataState.Loading -> _nextWeekWeatherForecastingViewState.update {
                        it.copy(
                            loadingState = !retryState,
                            loadingRefreshState = retryState,
                            errorState = false,
                        )
                    }

                    is DataState.Data -> {
                        _nextWeekWeatherForecastingViewState.update {
                            it.copy(
                                loadingState = false,
                                loadingRefreshState = false,
                                errorState = false,
                                nextWeekWeatherForecastData = dataState.data
                            )
                        }
                    }

                    is DataState.Error -> _nextWeekWeatherForecastingViewState.update {
                        it.copy(
                            loadingState = false,
                            loadingRefreshState = false,
                            errorState = true,
                            nextWeekWeatherForecastData = null
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }
}