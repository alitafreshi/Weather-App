package com.tafreshiali.weatherapp.presentation.current_weather.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tafreshiali.weatherapp.R
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherForecastingTopBar(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .padding(horizontal = WeatherAppTheme.size.medium),
        title = {
            WormPageIndicator(
                totalPages = pagerState.pageCount,
                currentPage = pagerState.currentPage,
                color = WeatherAppTheme.colorScheme.onSurface
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier.size(30.dp),
                painter = painterResource(R.drawable.ic_search),
                contentDescription = "search_icon"
            )
        },
        actions = {
            Icon(
                modifier = Modifier.size(30.dp),
                painter = painterResource(R.drawable.ic_menu),
                contentDescription = "menu_icon"
            )
        },
        colors = centerAlignedTopAppBarColors().copy(containerColor = Color.Transparent)
    )
}

@Composable
private fun WormPageIndicator(
    totalPages: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    indicatorSize: Dp = 6.dp,
    color: Color = Color.White,
    spacing: Dp = indicatorSize,
    selectedMultiplier: Int = 3
) {
    val rowWidth = remember {
        mutableStateOf((indicatorSize * (selectedMultiplier + (totalPages - 1))) + (spacing * (totalPages - 1)))
    }
    Row(
        modifier = modifier
            .requiredWidth(rowWidth.value),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(totalPages) { pageNumber ->
            val isSelected = pageNumber == currentPage
            val animatedWidth: Dp by animateDpAsState(
                if (isSelected) indicatorSize * selectedMultiplier else indicatorSize
            )
            Canvas(
                modifier = Modifier
                    .size(animatedWidth, indicatorSize),
                onDraw = {
                    drawRoundRect(
                        color = color,
                        cornerRadius = CornerRadius(indicatorSize.toPx() / 2),
                        size = Size(animatedWidth.toPx(), indicatorSize.toPx())
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CurrentWeatherForecastingTopBarPreview() {
    WeatherAppTheme {
        val pagerState = rememberPagerState(initialPage = 0, pageCount = { 4 })
        CurrentWeatherForecastingTopBar(pagerState = pagerState)
    }
}
