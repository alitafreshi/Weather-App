package com.tafreshiali.weatherapp.presentation.theme.design_system

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

data class AppShape(
    val medium: Shape,
    val large: Shape
)

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        medium = RectangleShape,
        large = RectangleShape
    )
}