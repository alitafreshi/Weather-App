package com.tafreshiali.weatherapp.presentation.theme.design_system

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tafreshiali.weatherapp.presentation.theme.OnSurface
import com.tafreshiali.weatherapp.presentation.theme.OnSurfaceDisabled
import com.tafreshiali.weatherapp.presentation.theme.PrimaryDark
import com.tafreshiali.weatherapp.presentation.theme.PrimaryLight
import com.tafreshiali.weatherapp.presentation.theme.SurfaceContainer30
import com.tafreshiali.weatherapp.presentation.theme.SurfaceContainer60
import com.tafreshiali.weatherapp.presentation.theme.SurfaceContainer70

private val lightColorScheme = AppColorSchema(
    primaryLight = PrimaryLight,
    primaryDark = PrimaryDark,
    onSurface = OnSurface,
    onSurfaceDisabled = OnSurfaceDisabled,
    surfaceContainer30 = SurfaceContainer30,
    surfaceContainer60 = SurfaceContainer60,
    surfaceContainer70 = SurfaceContainer70
)

private val typography = AppTypography(
    medium20 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
    ),
    regular9 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 9.sp
    ),
    regular14 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    regular7 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 7.sp
    ),
    regular8 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp
    ),
    regular11 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp
    ),
    semiBold7 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 7.sp
    ),
    bold7 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 7.sp
    ),
    bold8 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 8.sp
    )
)

private val shapes = AppShape(
    medium = RoundedCornerShape(10.dp),
    large = RoundedCornerShape(20.dp)
)

private val size = AppSize(
    large = 25.dp,
    medium = 16.dp,
    normal = 10.dp,
    small = 5.dp
)


@Composable
fun WeatherAppTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAppColorSchema provides lightColorScheme,
        LocalAppTypography provides typography,
        LocalAppShape provides shapes,
        LocalAppSize provides size,
        content = content
    )
}

object WeatherAppTheme {
    val colorScheme: AppColorSchema
        @Composable get() = LocalAppColorSchema.current

    val typography:AppTypography
        @Composable get() = LocalAppTypography.current

    val shapes:AppShape
        @Composable get() = LocalAppShape.current

    val size:AppSize
        @Composable get() = LocalAppSize.current
}

