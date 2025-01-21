package com.tafreshiali.weatherapp.presentation

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

    init {
        getWeatherForecastDataByCityName("Tehran")
    }

    private val _weatherData = MutableStateFlow(WeatherViewState())
    private val weatherData = _weatherData.asStateFlow()

    fun getWeatherForecastDataByCityName(cityName: String) {
        weatherRepository.getWeatherForecastingByLocation(cityName = cityName)
            .onEach { dataState ->
                when (dataState) {
                    DataState.Loading -> _weatherData.update {
                        it.copy(
                            loadingState = true,
                            errorState = false
                        )
                    }

                    is DataState.Data -> _weatherData.update {
                        it.copy(
                            loadingState = false,
                            errorState = false,
                            weatherData = dataState.data
                        )
                    }

                    is DataState.Error -> _weatherData.update {
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