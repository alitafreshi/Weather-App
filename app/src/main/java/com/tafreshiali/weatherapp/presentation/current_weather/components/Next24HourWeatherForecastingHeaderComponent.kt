package com.tafreshiali.weatherapp.presentation.current_weather.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tafreshiali.weatherapp.R
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
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(tvTodayTitle) {
                top.linkTo(imgNextWeek.top)
                bottom.linkTo(imgNextWeek.bottom)
                start.linkTo(parent.start)
            })

        Text(
            text = "Tomorrow",
            style = WeatherAppTheme.typography.regular8.copy(color = Color(0xFFD6996B)),
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(tvTomorrowTitle) {
                top.linkTo(imgNextWeek.top)
                bottom.linkTo(imgNextWeek.bottom)
                start.linkTo(tvTodayTitle.end, margin = 12.dp)
            }
        )
        Icon(
            modifier = Modifier.size(35.dp).constrainAs(imgNextWeek) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            },
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = "",
            tint = Color(0xFFD6996B)
        )
        Text(
            text = "Next Week",
            style = WeatherAppTheme.typography.regular8.copy(color = Color(0xFFD6996B)),
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(tvNextWeekTitle) {
                top.linkTo(imgNextWeek.top)
                bottom.linkTo(imgNextWeek.bottom)
                end.linkTo(imgNextWeek.start, margin = 6.dp)
            }
        )

        HorizontalDivider(
            modifier = Modifier.constrainAs(divider) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(imgNextWeek.bottom, margin = 8.dp)
                bottom.linkTo(parent.bottom)
            },
            thickness = 2.dp,
            color = Color(0xFFE2A272)
        )

        Surface(
            modifier = Modifier
                .height(5.dp)
                .width(12.dp)
                .constrainAs(badge) {
                    top.linkTo(divider.top)
                    bottom.linkTo(divider.bottom)
                    start.linkTo(tvTodayTitle.start)
                    end.linkTo(tvTodayTitle.end)
                },
            color = Color(0xFF313341),
            shape = WeatherAppTheme.shapes.large
        ) {
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Next24HourWeatherForecastingHeaderComponentPreview() {
    WeatherAppTheme {
        Next24HourWeatherForecastingHeaderComponent()
    }
}