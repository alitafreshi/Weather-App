package com.tafreshiali.weatherapp.presentation.next_week_weather.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tafreshiali.weatherapp.R
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NextWeekWeatherForecastingTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .padding(horizontal = WeatherAppTheme.size.medium),
        title = {
            Text(
                text = "Next 7 Days",
                style = WeatherAppTheme.typography.regular11.copy(fontSize = 17.sp)
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier.size(30.dp),
                painter = painterResource(R.drawable.ic_left_arrow),
                contentDescription = "search_icon"
            )
        },
        colors = centerAlignedTopAppBarColors().copy(containerColor = Color.Transparent)
    )
}

@Preview(showBackground = true)
@Composable
private fun NextWeekWeatherForecastingTopBarPreview() {
    WeatherAppTheme {
        NextWeekWeatherForecastingTopBar()
    }
}