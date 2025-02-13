package com.tafreshiali.weatherapp.data.remote.repository

import android.util.Log
import com.tafreshiali.weatherapp.data.remote.WeatherService
import com.tafreshiali.weatherapp.data.remote.model.ErrorDto
import com.tafreshiali.weatherapp.data.remote.model.toCurrentWeatherDetail
import com.tafreshiali.weatherapp.data.remote.model.toNextWeekWeatherForecastingList
import com.tafreshiali.weatherapp.data.state.DataState
import com.tafreshiali.weatherapp.domain.WeatherRepository
import com.tafreshiali.weatherapp.domain.model.CurrentWeatherDetail
import com.tafreshiali.weatherapp.domain.model.NextWeekWeatherForecastingDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val json: Json
) : WeatherRepository {
    override fun getWeatherForecastingByLocation(cityName: String): Flow<DataState<CurrentWeatherDetail>> =
        flow {
            emit(DataState.Loading)
            val forecastingRequest = weatherService.getWeatherDetailBaseOnLocationName(
                cityName = cityName,
                forecastingForDays = 2
            )
            val requestBody = forecastingRequest.body()
            when {
                forecastingRequest.isSuccessful && requestBody != null -> {
                    val currentWeatherDetail = requestBody.toCurrentWeatherDetail() ?: run {
                        emit(DataState.Error("There is an error with mapping dto to domain"))
                        return@flow
                    }
                    emit(DataState.Data(currentWeatherDetail))
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
            emit(DataState.Error(exception.message.orEmpty()))
            Log.d(
                "WeatherRepository",
                "there is a exception with weather api, exception is ${exception.message}"
            )
        }

    override fun getNextWeekWeatherForecastingByLocation(
        cityName: String
    ): Flow<DataState<List<NextWeekWeatherForecastingDetail>>> = flow {
        emit(DataState.Loading)
        val forecastingRequest = weatherService.getWeatherDetailBaseOnLocationName(
            cityName = cityName,
            forecastingForDays = 7
        )
        val requestBody = forecastingRequest.body()
        when {
            forecastingRequest.isSuccessful && requestBody != null -> {
                val currentWeatherDetail = requestBody.forecastDto?.toNextWeekWeatherForecastingList() ?: run {
                    emit(DataState.Error("There is an error with mapping dto to domain"))
                    return@flow
                }
                emit(DataState.Data(currentWeatherDetail))
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
        emit(DataState.Error(exception.message.orEmpty()))
        Log.d(
            "WeatherRepository",
            "there is a exception with weather api, exception is ${exception.message}"
        )
    }
}