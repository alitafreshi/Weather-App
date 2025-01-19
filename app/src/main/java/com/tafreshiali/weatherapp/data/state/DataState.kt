package com.tafreshiali.weatherapp.data.state

sealed interface DataState<out T> {
    data object Loading : DataState<Nothing>
    data class Data<T>(val data: T) : DataState<T>
    data class Error(val errorMessage: String) : DataState<Nothing>
}