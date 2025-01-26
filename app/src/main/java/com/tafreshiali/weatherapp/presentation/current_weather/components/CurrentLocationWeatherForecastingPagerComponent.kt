package com.tafreshiali.weatherapp.presentation.current_weather.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import com.tafreshiali.weatherapp.R
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme


@Composable
fun CurrentLocationWeatherForecastingPagerComponent(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    cityName: String,
    dateTime: String,
    temperature: String,
    temperatureUnit: String,
    weatherStatus: String,
    weatherLocalIcon: Int,
    weatherIconUrl: String
) {
    //TODO Here We should use view pager
    /*HorizontalPager(
        modifier = modifier,
        state = pagerState,
    ) {

    }*/
    PageContentComponent(
        cityName = cityName,
        dateTime = dateTime,
        temperature = temperature,
        temperatureUnit = temperatureUnit,
        weatherStatus = weatherStatus,
        weatherIcon = weatherLocalIcon,
        weatherIconUrl = weatherIconUrl
    )
}

@Composable
private fun PageContentComponent(
    cityName: String,
    dateTime: String,
    temperature: String,
    temperatureUnit: String,
    weatherStatus: String,
    weatherIcon: Int,
    weatherIconUrl: String
) {
    ConstraintLayout {
        val (cityNameComponent, temperatureComponent) = createRefs()
        CityNameComponent(
            cityName = cityName,
            dateTime = dateTime,
            modifier = Modifier
                .padding(start = 30.dp)
                .constrainAs(cityNameComponent) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })
        TemperatureComponent(
            temperature = temperature,
            temperatureUnit = temperatureUnit,
            weatherStatus = weatherStatus,
            weatherIcon = weatherIcon,
            weatherIconUrl = weatherIconUrl,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
                .constrainAs(temperatureComponent) {
                    top.linkTo(cityNameComponent.bottom)
                    start.linkTo(parent.start)
                })

    }
}

@Preview(showBackground = true)
@Composable
private fun PageContentComponentPreview() {
    WeatherAppTheme {
        PageContentComponent(
            cityName = "Tehran, Iran",
            dateTime = "Tue, Jun 30",
            temperature = "25",
            temperatureUnit = "C",
            weatherStatus = "Sunny",
            weatherIcon = R.drawable.ic_sun_and_cloud,
            weatherIconUrl = ""
        )
    }
}

@Composable
private fun CityNameComponent(
    modifier: Modifier = Modifier,
    cityName: String,
    dateTime: String,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.Start) {
        Text(text = cityName, fontSize = 40.sp, style = WeatherAppTheme.typography.medium20)
        Text(
            modifier = Modifier.padding(top = WeatherAppTheme.size.small),
            text = dateTime,
            fontSize = 15.sp,
            style = WeatherAppTheme.typography.regular9,
            color = Color(0xFF9A938C)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CityNameComponentPreview() {
    WeatherAppTheme {
        CityNameComponent(cityName = "Tehran,\nIran", dateTime = "Tue, Jun 30")
    }
}

@Composable
private fun TemperatureComponent(
    modifier: Modifier = Modifier,
    temperature: String,
    temperatureUnit: String,
    weatherStatus: String,
    weatherIcon: Int,
    weatherIconUrl: String
) {
    ConstraintLayout(modifier = modifier.padding(horizontal = 40.dp)) {
        val (
            tvTemperatureNumber,
            tvTemperatureSymbol,
            tvTemperatureUnit,
            tvWeatherStatus,
            imgWeatherStatus
        ) = createRefs()

        Text(text = temperatureUnit, modifier = Modifier.constrainAs(tvTemperatureUnit) {
            top.linkTo(parent.top, margin = 10.dp)
            end.linkTo(parent.end)
        })

        Text(text = "o", modifier = Modifier.constrainAs(tvTemperatureSymbol) {
            top.linkTo(parent.top)
            end.linkTo(tvTemperatureUnit.start, margin = 5.dp)
        })

        Text(
            text = temperature,
            style = WeatherAppTheme.typography.bold43,
            fontSize = 65.sp,
            modifier = Modifier.constrainAs(tvTemperatureNumber) {
                top.linkTo(parent.top, margin = 5.dp)
                end.linkTo(tvTemperatureSymbol.start, margin = 10.dp)
            })

        Text(
            text = weatherStatus,
            style = WeatherAppTheme.typography.regular14,
            modifier = Modifier.constrainAs(tvWeatherStatus) {
                top.linkTo(tvTemperatureNumber.bottom)
                start.linkTo(tvTemperatureNumber.start)
                end.linkTo(tvTemperatureNumber.end)
            })

        AsyncImage(
            model = weatherIconUrl,
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
                .constrainAs(imgWeatherStatus) {
                    start.linkTo(parent.start)
                    end.linkTo(tvTemperatureNumber.start, margin = 35.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                })
    }
}

@Preview(showBackground = true)
@Composable
private fun TemperatureComponentPreview() {
    WeatherAppTheme {
        TemperatureComponent(
            temperature = "25",
            temperatureUnit = "C",
            weatherStatus = "Sunny",
            weatherIcon = R.drawable.ic_sun_and_cloud,
            weatherIconUrl = ""
        )
    }
}

