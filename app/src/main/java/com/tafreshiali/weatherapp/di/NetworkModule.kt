package com.tafreshiali.weatherapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.tafreshiali.weatherapp.data.remote.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.weatherapi.com/"

    @Singleton
    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)


    @Singleton
    @Provides
    fun provideOkHttpClient(chcukerInterceptor: ChuckerInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(chcukerInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideRetrofitKotlinxSerialization(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json; charset=UTF8".toMediaType()
                )
            )
            .client(okHttpClient)
            .build()
    }
}