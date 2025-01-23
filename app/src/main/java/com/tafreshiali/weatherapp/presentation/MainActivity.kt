package com.tafreshiali.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                val colorStops = arrayOf(
                    0.0f to WeatherAppTheme.colorScheme.primaryLight,
                    1f to WeatherAppTheme.colorScheme.primaryDark
                )
                val brush = Brush.linearGradient(colorStops = colorStops)
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    AppNavigation(
                        modifier = Modifier
                            .background(brush)
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}