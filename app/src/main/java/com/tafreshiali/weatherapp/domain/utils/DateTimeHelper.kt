package com.tafreshiali.weatherapp.domain.utils

import android.util.Log
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.coroutines.cancellation.CancellationException

// Converter function
fun convertServerDateTimeToTimeOnlyFormatString(dateTimeString: String): String? {
    val conversionResult = runCatching {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val dateTime = LocalDateTime.parse(dateTimeString, inputFormatter)
        val outputFormatter = DateTimeFormatter.ofPattern("HH:mm")
        dateTime.format(outputFormatter) // Format the date to "HH:mm"
    }
    conversionResult.fold(
        onSuccess = { time -> return time },
        onFailure = { exception ->
            exception.printStackTrace()
            if (exception is CancellationException) {
                throw exception
            }
            return null
        })
}

fun convertServerDateTimeStringToDateTimeFormat(serverDateTimeString: String): LocalDateTime? {
    val conversionResult = runCatching {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        LocalDateTime.parse(serverDateTimeString, inputFormatter)
    }
    conversionResult.fold(
        onSuccess = { time -> return time },
        onFailure = { exception ->
            exception.printStackTrace()
            if (exception is CancellationException) {
                throw exception
            }
            return null
        })
}

// Comparator function (returns Boolean for equality)
fun areTimesEqual(serverTime: String, currentTime: String): Boolean {
    val compareResult = runCatching {
        val serverDate = serverTime.convertTimeStringToTime() // Parse server time to Date
        val currentDate = currentTime.convertTimeStringToTime() // Parse current time to Date
        serverDate == currentDate // Compare the two Date objects
    }
    compareResult.fold(
        onSuccess = { isEqualToNow -> return isEqualToNow },
        onFailure = { exception ->
            exception.printStackTrace()
            if (exception is CancellationException) {
                throw exception
            }
            return false
        })
}

fun areTimesEqual(serverTime: LocalDateTime, currentTime: LocalDateTime): Boolean =
    serverTime == currentTime

fun String.convertTimeStringToTime(): LocalTime? {
    val timeStringToDateFormatConversionResult = runCatching {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        LocalTime.parse(this, formatter)
    }
    timeStringToDateFormatConversionResult.fold(
        onSuccess = { date -> return date },
        onFailure = { exception ->
            exception.printStackTrace()
            if (exception is CancellationException) {
                throw exception
            }
            return null
        })
}

fun LocalTime.convertTimeToString(): String? {
    val timeToStringConversionResult = runCatching {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        this.format(formatter)
    }
    timeToStringConversionResult.fold(
        onSuccess = { time -> return time },
        onFailure = { exception ->
            exception.printStackTrace()
            if (exception is CancellationException) {
                throw exception
            }
            return null
        })
}

// Get current time
fun getCurrentDateTime(): LocalDateTime? {
    val currentTimeResult = runCatching {
        val now = LocalDateTime.now()
        val minutes = now.minute
        // Round up to the next hour if necessary
        now.plusHours(if (minutes > 0) 1 else 0)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
    }
    currentTimeResult.fold(
        onSuccess = { currentTime -> return currentTime },
        onFailure = { exception ->
            exception.printStackTrace()
            if (exception is CancellationException) {
                throw exception
            }
            return null
        })
}

// Generate time range
fun generateNext24HourLaterTime(): List<LocalDateTime> = buildList {
    val currentTime = getCurrentDateTime() ?: return@buildList
    add(currentTime)
    repeat(23) {
        add(currentTime.plusHours(1))
    }
}

fun LocalDateTime.isInNext24Hour(timeRange: List<LocalDateTime>): Boolean {
    return timeRange.any { it == this }
}

fun String.convertLocalDateTimeToHumanReadableFormat(): String? {
    val parseResult = runCatching {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val dateTime = LocalDateTime.parse(this, inputFormatter)
        val outputFormatter = DateTimeFormatter.ofPattern("EEE, MMM dd")
        dateTime.format(outputFormatter)
    }
    parseResult.fold(
        onSuccess = { readableDateTime -> return readableDateTime },
        onFailure = { exception ->
            Log.d(
                "DateTimeConverter",
                "Can't convert date time to human readable format reason is ${exception.message}"
            )
            if (exception is CancellationException) {
                throw exception
            }
            return null
        }
    )
}
