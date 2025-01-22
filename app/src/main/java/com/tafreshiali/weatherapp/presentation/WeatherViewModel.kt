package com.tafreshiali.weatherapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tafreshiali.weatherapp.data.state.DataState
import com.tafreshiali.weatherapp.domain.WeatherRepository
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

    init {
        Log.d("WEATHER_VIEWMODEL", "INIT BLOCK IS CALLED")
        getWeatherForecastDataByCityName("Tehran")
    }

    fun getWeatherForecastDataByCityName(cityName: String) {
        weatherRepository.getWeatherForecastingByLocation(cityName = cityName)
            .onEach { dataState ->
                when (dataState) {
                    DataState.Loading -> _weatherViewState.update {
                        it.copy(
                            loadingState = true,
                            errorState = false
                        )
                    }

                    is DataState.Data -> _weatherViewState.update {
                        it.copy(
                            loadingState = false,
                            errorState = false,
                            weatherData = dataState.data
                        )
                    }

                    is DataState.Error -> _weatherViewState.update {
                        it.copy(
                            loadingState = false,
                            errorState = true
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}