package com.tafreshiali.weatherapp.presentation.current_weather.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme


@Composable
fun Next24HourWeatherForecastingHeaderComponent(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier.padding(vertical = 10.dp)) {
        val (tvTodayTitle, tvTomorrowTitle, tvNextWeekTitle, imgNextWeek, divider, badge) = createRefs()

        Text(
            text = "Today",
            style = WeatherAppTheme.typography.bold8,
            modifier = Modifier.constrainAs(tvTodayTitle) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            })
    }
}

@Preview
@Composable
private fun Next24HourWeatherForecastingHeaderComponentPreview() {
    WeatherAppTheme {
        Next24HourWeatherForecastingHeaderComponent()
    }
}