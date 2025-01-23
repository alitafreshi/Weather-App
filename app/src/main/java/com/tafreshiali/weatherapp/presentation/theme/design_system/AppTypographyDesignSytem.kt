package com.tafreshiali.weatherapp.presentation.theme.design_system

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

data class AppTypography(
    val medium20: TextStyle,
    val regular9: TextStyle,
    val regular14: TextStyle,
    val regular7: TextStyle,
    val regular8: TextStyle,
    val regular11: TextStyle,
    val semiBold7: TextStyle,
    val bold7: TextStyle,
    val bold8: TextStyle
)

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        medium20 = TextStyle.Default,
        regular9 = TextStyle.Default,
        regular14 = TextStyle.Default,
        regular7 = TextStyle.Default,
        regular8 = TextStyle.Default,
        regular11 = TextStyle.Default,
        semiBold7 = TextStyle.Default,
        bold7 = TextStyle.Default,
        bold8 = TextStyle.Default
    )
}