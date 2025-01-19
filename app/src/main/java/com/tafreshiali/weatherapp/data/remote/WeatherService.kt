package com.tafreshiali.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("v1/forecast?current=is_day,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation_probability,weather_code,wind_speed_180m&daily=weather_code,sunrise,sunset&timezone=auto")
    suspend fun getWeatherDetailBaseOnLocation(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    )
}