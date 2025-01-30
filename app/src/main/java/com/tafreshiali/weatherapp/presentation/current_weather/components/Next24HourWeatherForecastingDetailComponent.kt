package com.tafreshiali.weatherapp.presentation.current_weather.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.tafreshiali.weatherapp.domain.model.HourlyWeatherForecasting
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce

@OptIn(FlowPreview::class)
@Composable
fun Next24HourWeatherForecastingDetailComponent(
    modifier: Modifier = Modifier,
    next24HourWeatherForecastingData: List<HourlyWeatherForecasting>
) {
    val listState = rememberLazyListState()
    var firstVisibleItem by remember {
        mutableStateOf<HourlyWeatherForecasting?>(null)
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .debounce(300)
            .collect { index ->
                firstVisibleItem = next24HourWeatherForecastingData[index]
            }
    }
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        state = listState
    ) {
        items(
            next24HourWeatherForecastingData,
            key = { it.time.toInt() }) { item: HourlyWeatherForecasting ->
            val animatedSelectedColor by animateColorAsState(
                targetValue = if (item == firstVisibleItem) Color.White else Color(0x4DFFFFFF),
            )

            Surface(
                modifier = Modifier,
                color = Color(0x4DFFFFFF),
                shape = RoundedCornerShape(55.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier.padding(horizontal = 9.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = item.time,
                        style = WeatherAppTheme.typography.regular7.copy(fontSize = 15.sp),
                    )
                    AsyncImage(
                        modifier = Modifier.size(34.dp),
                        model = item.weatherCondition.conditionIconUrl,
                        contentDescription = ""
                    )
                    Text(
                        text = item.temperature,
                        style = WeatherAppTheme.typography.bold7.copy(fontSize = 15.sp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Next24HourWeatherForecastingDetailComponentPreview() {
    WeatherAppTheme {
        Next24HourWeatherForecastingDetailComponent(
            next24HourWeatherForecastingData = listOf(
                HourlyWeatherForecasting(
                    time = "12:00",
                    temperature = "15",
                    weatherCondition = com.tafreshiali.weatherapp.domain.model.WeatherCondition(
                        conditionName = "Sunny",
                        conditionIconUrl = "",
                        conditionType = com.tafreshiali.weatherapp.domain.model.WeatherConditionType.SUNNY
                    )
                ),
                HourlyWeatherForecasting(
                    time = "13:00",
                    temperature = "17",
                    weatherCondition = com.tafreshiali.weatherapp.domain.model.WeatherCondition(
                        conditionName = "Cloudy",
                        conditionIconUrl = "",
                        conditionType = com.tafreshiali.weatherapp.domain.model.WeatherConditionType.CLOUDY
                    )
                )
            )
        )
    }
}


