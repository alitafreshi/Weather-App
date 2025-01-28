package com.tafreshiali.weatherapp.presentation.theme.design_system

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColorSchema(
    val primaryLight: Color,
    val primaryDark: Color,
    val onSurface: Color,
    val onSurfaceDisabled: Color,
    val surfaceContainer30: Color,
    val surfaceContainer50: Color,
    val surfaceContainer60: Color,
    val surfaceContainer70: Color
)

val LocalAppColorSchema = staticCompositionLocalOf {
    AppColorSchema(
        primaryLight = Color.Unspecified,
        primaryDark = Color.Unspecified,
        onSurface = Color.Unspecified,
        onSurfaceDisabled = Color.Unspecified,
        surfaceContainer30 = Color.Unspecified,
        surfaceContainer50 = Color.Unspecified,
        surfaceContainer60 = Color.Unspecified,
        surfaceContainer70 = Color.Unspecified
    )
}