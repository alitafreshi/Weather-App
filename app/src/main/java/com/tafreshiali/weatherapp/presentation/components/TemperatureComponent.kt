package com.tafreshiali.weatherapp.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme

@Composable
fun TemperatureComponent(
    modifier: Modifier = Modifier,
    temperature: String,
) {
    ConstraintLayout(modifier = modifier) {
        val (tvTemperature, tvDegree) = createRefs()

        Text(
            modifier = Modifier.constrainAs(tvTemperature) {
                top.linkTo(tvDegree.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(tvDegree.start, margin = 3.dp)
            },
            text = temperature,
            style = WeatherAppTheme.typography.bold7.copy(fontSize = 15.sp)
        )
        Text(
            modifier = Modifier.constrainAs(tvDegree) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            },
            text = "o",
            style = WeatherAppTheme.typography.regular7.copy(fontSize = 10.sp)
        )
    }
}

@Preview
@Composable
private fun TemperatureComponentPreview() {
    WeatherAppTheme {
        TemperatureComponent(temperature = "5")
    }
}