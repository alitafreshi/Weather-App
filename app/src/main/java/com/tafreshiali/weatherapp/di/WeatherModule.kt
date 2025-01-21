package com.tafreshiali.weatherapp.di

import com.tafreshiali.weatherapp.data.remote.repository.WeatherRepositoryImpl
import com.tafreshiali.weatherapp.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class WeatherModule {
    @ViewModelScoped
    @Binds
    abstract fun provideWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}