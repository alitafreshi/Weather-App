package com.tafreshiali.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("v1/forecast.json")
    suspend fun getWeatherDetailBaseOnLocationName(
        @Query("q") cityName: String,
        @Query("days") forecastingForDays: Int
    )
}