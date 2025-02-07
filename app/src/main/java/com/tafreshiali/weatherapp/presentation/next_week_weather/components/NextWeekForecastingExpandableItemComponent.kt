package com.tafreshiali.weatherapp.presentation.next_week_weather.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.tafreshiali.weatherapp.R
import com.tafreshiali.weatherapp.domain.model.NextWeekWeatherForecastingDetail
import com.tafreshiali.weatherapp.presentation.components.TemperatureComponent
import com.tafreshiali.weatherapp.presentation.current_weather.components.AppIconComponent
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme
import com.tafreshiali.weatherapp.presentation.utils.clickableWithoutRipple


@Composable
fun NextWeekForecastingExpandableItemComponent(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    weekDayWeatherForecastingDetail: NextWeekWeatherForecastingDetail,
    onClickExpanded: (id: Int) -> Unit
) {
    val transition = updateTransition(targetState = expanded, label = "transition")
    val animateExpandedItemBgColor by transition.animateColor(
        label = "animateExpandedItemBgColor",
        transitionSpec = { tween(durationMillis = 500) },
        targetValueByState = { state ->
            if (state) {
                WeatherAppTheme.colorScheme.surfaceContainer70
            } else {
                WeatherAppTheme.colorScheme.surfaceContainer30
            }
        }
    )

    val animateExpandedItemStrokeColor by transition.animateColor(
        label = "animateExpandedItemStrokeColor",
        transitionSpec = { tween(durationMillis = 500) },
        targetValueByState = { state ->
            if (state) {
                WeatherAppTheme.colorScheme.surfaceContainer70
            } else {
                WeatherAppTheme.colorScheme.surfaceContainer30
            }
        }
    )

    val animatePadding by transition.animateDp(
        label = "animatePadding",
        transitionSpec = { tween(durationMillis = 500) },
        targetValueByState = { state ->
            if (state) {
                16.dp // Expanded state padding
            } else {
                0.dp // Collapsed state padding (adjust as needed)
            }
        }
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickableWithoutRipple(
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    onClickExpanded(weekDayWeatherForecastingDetail.id)
                }),
        color = animateExpandedItemBgColor,
        shape = WeatherAppTheme.shapes.large,
        border = BorderStroke(
            width = 0.5.dp,
            color = animateExpandedItemStrokeColor
        )
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(animatePadding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = weekDayWeatherForecastingDetail.dayTitle,
                    style = WeatherAppTheme.typography.semiBold7.copy(fontSize = 15.sp),
                    modifier = Modifier.weight(1f)
                )
                TemperatureComponent(
                    temperature = weekDayWeatherForecastingDetail.temperature,
                    modifier = Modifier.weight(0.1f)
                )
                Spacer(modifier = Modifier.width(5.dp))
                AsyncImage(
                    model = weekDayWeatherForecastingDetail.weatherCondition.conditionIconUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .weight(0.2f)
                        .size(45.dp)
                )
            }
            ExpandableContent(
                isExpanded = expanded,
                windSpeed = weekDayWeatherForecastingDetail.windSpeed,
                humidity = weekDayWeatherForecastingDetail.humidity,
                rainFallInMillimeter = weekDayWeatherForecastingDetail.rainFallInMillimeter.toString(),
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun ExpandableContent(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    windSpeed: String,
    humidity: String,
    rainFallInMillimeter: String
) {
    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(
                durationMillis = 500
            )
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(
                durationMillis = 500
            )
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Bottom,
            animationSpec = tween(
                durationMillis = 500
            )
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = 500
            )
        )
    }
    AnimatedVisibility(
        modifier = modifier,
        visible = isExpanded,
        enter = enterTransition,
        exit = exitTransition
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NextWeekForecastingExpandableItemComponent(
                icon = R.drawable.ic_rain_fall,
                title = "$rainFallInMillimeter cm"
            )
            NextWeekForecastingExpandableItemComponent(
                icon = R.drawable.ic_wind,
                title = "$windSpeed km/h"
            )
            NextWeekForecastingExpandableItemComponent(
                icon = R.drawable.ic_humidity,
                title = "$humidity%"
            )
        }
    }
}


@Composable
private fun NextWeekForecastingExpandableItemComponent(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppIconComponent(icon = icon)
        Text(text = title, style = WeatherAppTheme.typography.semiBold7.copy(fontSize = 10.sp))
    }
}

@Preview(showBackground = true)
@Composable
private fun NextWeekForecastingExpandableItemComponentPreview() {
    WeatherAppTheme {
        NextWeekForecastingExpandableItemComponent(
            icon = R.drawable.ic_humidity,
            title = "50%"
        )
    }
}