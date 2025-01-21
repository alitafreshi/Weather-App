package com.tafreshiali.weatherapp.data.remote.repository

import android.util.Log
import com.tafreshiali.weatherapp.data.remote.WeatherService
import com.tafreshiali.weatherapp.data.remote.model.ErrorDto
import com.tafreshiali.weatherapp.data.remote.model.WeatherApiDto
import com.tafreshiali.weatherapp.data.state.DataState
import com.tafreshiali.weatherapp.domain.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val json: Json
) : WeatherRepository {
    override fun getWeatherForecastingByLocation(cityName: String): Flow<DataState<WeatherApiDto>> =
        flow {
            emit(DataState.Loading)
            val forecastingRequest = weatherService.getWeatherDetailBaseOnLocationName(
                cityName = cityName,
                forecastingForDays = 7
            )
            val requestBody = forecastingRequest.body()
            when {
                forecastingRequest.isSuccessful && requestBody != null -> {
                    emit(DataState.Data(requestBody))
                }

                else -> {
                    //handle error
                    val plainErrorBody = forecastingRequest.errorBody()?.string()
                        ?: throw IllegalAccessException("error body is null")
                    val errorResponse = json.decodeFromString<ErrorDto>(plainErrorBody)
                    val errorMessage = errorResponse.message
                        ?: throw IllegalAccessError("error message is null")
                    emit(DataState.Error(errorMessage = errorMessage))
                }
            }
        }.catch { exception ->
            Log.d(
                "WeatherRepository",
                "there is a exception with weather api, exception is ${exception.message}"
            )
        }
}