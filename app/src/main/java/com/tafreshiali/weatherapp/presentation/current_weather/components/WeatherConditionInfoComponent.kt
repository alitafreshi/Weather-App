package com.tafreshiali.weatherapp.presentation.current_weather.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import com.tafreshiali.weatherapp.R
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme

@Composable
fun WeatherConditionInfoComponent(
    modifier: Modifier = Modifier,
    conditionIcon: Int = R.drawable.ic_rain_fall,
    conditionTitle: String = "RainFall",
    conditionValue: String = "20cm"
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = WeatherAppTheme.colorScheme.surfaceContainer30,
        shape = WeatherAppTheme.shapes.large,
        border = BorderStroke(
            width = 0.5.dp,
            color = WeatherAppTheme.colorScheme.surfaceContainer50
        )
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(
                top = 15.dp,
                bottom = 15.dp,
                start = 20.dp,
                end = 28.dp
            )
        ) {
            val (imgCondition, tvConditionTitle, tvConditionValue) = createRefs()
            AppIconComponent(
                modifier = Modifier.constrainAs(imgCondition) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
                icon = conditionIcon
            )
            Text(
                text = conditionTitle,
                style = WeatherAppTheme.typography.regular11,
                fontSize = 15.sp,
                modifier = Modifier.constrainAs(tvConditionTitle) {
                    top.linkTo(imgCondition.top)
                    start.linkTo(imgCondition.end, margin = 20.dp)
                    bottom.linkTo(imgCondition.bottom)
                }
            )
            Text(
                text = conditionValue,
                style = WeatherAppTheme.typography.regular11,
                fontSize = 15.sp,
                modifier = Modifier.constrainAs(tvConditionValue) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF39876)
@Composable
private fun WeatherConditionInfoComponentPreview() {
    WeatherAppTheme {
        WeatherConditionInfoComponent()
    }
}


@Composable
fun AppIconComponent(
    modifier: Modifier = Modifier,
    shadowElevation: Dp = 5.dp,
    iconSize: Dp = 48.dp,
    iconPadding: Dp = 3.dp,
    icon: Int
) {
    Surface(
        modifier = modifier,
        color = Color.White,
        shape = WeatherAppTheme.shapes.large,
        shadowElevation = shadowElevation
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(iconPadding)
                .size(iconSize),
            model = icon,
            contentDescription = "",
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF39876)
@Composable
private fun AppIconComponentPreview() {
    WeatherAppTheme {
        AppIconComponent(icon = R.drawable.ic_rain_fall)
    }
}