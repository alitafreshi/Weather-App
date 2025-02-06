package com.tafreshiali.weatherapp.presentation

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val colorStops = arrayOf(
                0.0f to WeatherAppTheme.colorScheme.primaryLight,
                1f to WeatherAppTheme.colorScheme.primaryDark
            )
            val brush = Brush.linearGradient(colorStops = colorStops)
            WeatherAppTheme {
                SetLightStatusBarIcons()
                AppNavigation(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush)
                )
            }
        }
    }
}

@Composable
fun SetLightStatusBarIcons() {
    val view = LocalView.current
    val window = (view.context as Activity).window
    val windowInsetsController = WindowInsetsControllerCompat(window, view)
    // Set the status bar icons to dark (black)
    windowInsetsController.isAppearanceLightStatusBars = true
}